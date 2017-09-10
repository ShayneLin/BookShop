package com.bookshop.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bookshop.domain.Book;
import com.bookshop.pager.Expression;
import com.bookshop.pager.PageBean;
import com.bookshop.pager.PageConstants;
import com.bookshop.utils.DBHelper;

public class BookDao extends HibernateDaoSupport {
	// 初始化HibenateDaoSupport
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 删除图书
	public void delete(final String bid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete from t_book where bid=?";
				Query query = session.createSQLQuery(sql);
				query.setParameter(0, bid);
				query.executeUpdate();
				return null;
			}

		});

		// tx.commit();
		// DBHelper.closeSession();
	}

	// Admin通过分类的cid查找该分类下的图书的数量(用于删除二级分类)
	public int findBookNumByCid(final String cid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		final String hql = "select count(*) from Book where cid = ?";
		// List list = this.getHibernateTemplate().find(hql, cid);
		Number number = this.getHibernateTemplate().execute(
				new HibernateCallback<Number>() {

					@Override
					public Number doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter(0, cid);
						Number number = (Number) query.uniqueResult();
						return number;
					}

				});
		// tx.commit();
		// DBHelper.closeSession();
		return number == null ? 0 : number.intValue();
	}

	// 这是管理员的loadBookByBid方法
	public Book loadBookByBid(String bid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String hql = "select b from Book b,Category c where c.cid = b.cid and bid = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, bid);
		// Book book = (Book) query.uniqueResult();
		// tx.commit();
		// DBHelper.closeSession();
		Book book = null;
		List list = this.getHibernateTemplate().find(hql, bid);
		if (list.size() > 0)
			book = (Book) list.get(0);
		return book;
	}

	// 编辑图书操作
	public void edit(final Book book, final String cid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "update t_book set bname= :bname,author= :author,price= :price,currPrice= :currPrice,"
						+ "discount= :discount,press= :press,publishtime= :publishtime,edition= :edition,pageNum= :pageNum,wordNum= :wordNum,"
						+ "printtime= :printtime,booksize= :booksize,paper= :paper,cid= :cid where bid= :bid";
				Query query = session.createSQLQuery(sql);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bname", book.getBname());
				map.put("author", book.getAuthor());
				map.put("price", book.getPrice());
				map.put("currPrice", book.getCurrPrice());
				map.put("discount", book.getDiscount());
				map.put("press", book.getPress());
				map.put("publishtime", book.getPublishtime());
				map.put("edition", book.getEdition());
				map.put("pageNum", book.getPageNum());
				map.put("wordNum", book.getWordNum());
				map.put("printtime", book.getPrinttime());
				map.put("booksize", book.getBooksize());
				map.put("paper", book.getPaper());
				map.put("cid", cid);
				map.put("bid", book.getBid());
				query.setProperties(map);
				query.executeUpdate();
				return null;
			}

		});
		// tx.commit();
		// DBHelper.closeSession();
	}

	// <--------------------分割线-------------------------------->
	// 普通用户的，根据bid返回一本书，给用户显示该书的详情
	public Book findBookByBid(String bid) {
		// Session session = DBHelper.getSession();
		// String sql = "select * from T_Book where bid = ?";
		String hql = "from Book where bid = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, bid);
		// Book book = (Book) query.uniqueResult();
		Book book = null;
		List list = this.getHibernateTemplate().find(hql, bid);
		if (list.size() > 0)
			book = (Book) list.get(0);
		return book;
	}

	// 按照分类查询
	public PageBean<Book> findByCategory(String cid, int pc) {
		Expression expression = new Expression("cid", "=", cid);
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(expression);
		PageBean<Book> pageBean = findByCriteria(exprList, pc);
		return pageBean;
	}

	// 按照书名查询
	public PageBean<Book> findByBookName(String bname, int pc) {
		Expression expression = new Expression("bname", "like", "%" + bname
				+ "%");
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(expression);
		PageBean<Book> pageBean = findByCriteria(exprList, pc);
		return pageBean;
	}

	// 按照作者来查询
	public PageBean<Book> findByAuthor(String bauthor, int pc) {
		Expression expression = new Expression("author", "like", "%" + bauthor
				+ "%");
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(expression);
		PageBean<Book> pageBean = findByCriteria(exprList, pc);
		return pageBean;
	}

	// 按出版社查询
	public PageBean<Book> findByPress(String press, int pc) {
		Expression expression = new Expression("press", "like", "%" + press
				+ "%");
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(expression);
		return findByCriteria(exprList, pc);
	}

	// 高级查询，复杂查询
	public PageBean<Book> findByCombination(Book criteria, int pc) {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("bname", "like", "%" + criteria.getBname()
				+ "%"));
		exprList.add(new Expression("author", "like", "%"
				+ criteria.getAuthor() + "%"));
		exprList.add(new Expression("press", "like", "%" + criteria.getPress()
				+ "%"));
		PageBean<Book> pageBean = findByCriteria(exprList, pc);
		return pageBean;

	}

	private PageBean<Book> findByCriteria(List<Expression> exprList,
			final int pc) {
		// Session session = DBHelper.getSession();

		// 1. 得到ps
		final int ps = PageConstants.BOOK_PAGE_SIZE;

		// 2. 得到wheresql子句

		final StringBuilder wheresql = new StringBuilder(" where 1=1 ");
		final Map<String, Object> params = new HashMap<String, Object>();
		for (Expression expression : exprList) {
			wheresql.append(" and " + expression.getName())
					.append(" " + expression.getOperator()).append(" ");
			if (!expression.getOperator().equals("is null")) {
				// 要改为命令参数，用了setProperties(Map map),不能用命令参数
				wheresql.append(" :" + expression.getName());
				// 参数交给Map
				params.put(expression.getName(), expression.getValue());
			}
		}

		// 3.查询总计录数
		// ！！！注意第三个参数要转成数组传过去，否则会出错，number一直得到0页
		Number number = this.getHibernateTemplate().execute(
				new HibernateCallback<Number>() {

					@Override
					public Number doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "select count(*) from  Book " + wheresql;
						Query query1 = session.createQuery(hql);
						query1.setProperties(params);
						Number number = (Number) query1.uniqueResult();
						return number;
					}
				});
		int tr = number.intValue();
		// 4得到分页的记录select * from T_Book where 1=1 and author like '%java' order
		List<Book> beanList = this.getHibernateTemplate().execute(
				new HibernateCallback<List<Book>>() {

					@Override
					public List<Book> doInHibernate(Session session)
							throws HibernateException, SQLException {
						String allsql = "from Book " + wheresql
								+ " order by orderBy";
						Query query2 = session.createQuery(allsql);
						query2.setProperties(params);
						// 当前页首行记录的下标
						query2.setFirstResult((pc - 1) * ps);
						query2.setMaxResults(ps);
						List<Book> beanList = query2.list();
						return beanList;
					}
				});
		// 5生成PageBean
		PageBean<Book> pageBean = new PageBean<Book>();
		pageBean.setPc(pc);
		pageBean.setTr(tr);
		pageBean.setPs(ps);
		// 总页数
		pageBean.setBeanList(beanList);
		// 关闭会话
		// DBHelper.closeSession();
		return pageBean;
	}

	// 增加图书
	public void add(final Book book) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "insert into t_book(bid,bname,author,price,currPrice,"
						+ "discount,press,publishtime,edition,pageNum,wordNum,printtime,"
						+ "booksize,paper,cid,image_w,image_b)"
						+ " values(:bid,:bname,:author,:price,:currPrice,:discount,:press,:publishtime,:edition,:pageNum,:wordNum,:printtime,"
						+ ":booksize,:paper,:cid,:image_w,:image_b)";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bid", book.getBid());
				map.put("bname", book.getBname());
				map.put("author", book.getAuthor());
				map.put("price", book.getPrice());
				map.put("currPrice", book.getCurrPrice());
				map.put("discount", book.getDiscount());
				map.put("press", book.getPress());
				map.put("publishtime", book.getPublishtime());
				map.put("edition", book.getEdition());
				map.put("pageNum", book.getPageNum());
				map.put("wordNum", book.getWordNum());
				map.put("printtime", book.getPrinttime());
				map.put("booksize", book.getBooksize());
				map.put("paper", book.getPaper());
				map.put("cid", book.getCid());
				map.put("image_w", book.getImage_w());
				map.put("image_b", book.getImage_b());
				Query query = session.createSQLQuery(sql);
				query.setProperties(map);
				query.executeUpdate();
				return null;
			}

		});
		// tx.commit();
		// DBHelper.closeSession();
	}
}
