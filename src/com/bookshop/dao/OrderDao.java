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

import com.bookshop.domain.Order;
import com.bookshop.pager.Expression;
import com.bookshop.pager.PageBean;
import com.bookshop.pager.PageConstants;
import com.bookshop.utils.DBHelper;

public class OrderDao extends HibernateDaoSupport {

	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// Admin查询所有
	public PageBean<Order> findAll(int pc) {
		List<Expression> exprList = new ArrayList<Expression>();
		return findByCriteria(exprList, pc);
	}

	// Admin按照状态查询
	public PageBean<Order> findByStatus(int status, int pc) {
		return findOrderByStatus(status, pc);
	}

	// ------------------------------------------》

	// 查找对应订单的状态
	public int findOrderStatus(final String oid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		final String hql = "select status from Order where oid = ?";

		Number number = this.getHibernateTemplate().execute(
				new HibernateCallback<Number>() {

					@Override
					public Number doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter(0, oid);
						Number number = (Number) query.uniqueResult();
						return number;
					}
				});
		// tx.commit();
		// DBHelper.closeSession();
		return number.intValue();
	}

	// 修改对应订单的状态
	public void updateOrderStatus(final String oid, final int status) {

		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "update T_Order set status = ? where oid = ?";
				Query query = session.createSQLQuery(sql);
				query.setParameter(0, status);
				query.setParameter(1, oid);
				query.executeUpdate();
				return null;
			}
		});
		// tx.commit();
		// DBHelper.closeSession();
	}

	// 查看订单号的对应的详细信息
	public Order loadOrderByOid(String oid) {
		// Session session = DBHelper.getSession();
		// session.clear();
		// Transaction tx = session.beginTransaction();
		// 1生成hql语句
		String hql = "from Order where oid = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, oid);
		// Order order = (Order) query.uniqueResult();
		Order order = null;
		List list = this.getHibernateTemplate().find(hql, oid);
		if (list.size() > 0)
			order = (Order) list.get(0);
		// 2.加载该订单下的所有商品的信息
		// tx.commit();
		// DBHelper.closeSession();
		return order;
	}

	// 生成订单
	public void createOrder(Order order) {
		// 1.先生成order表的插入语句,插入order表
		// Session session = DBHelper.getSession();
		// session.clear();
		// Transaction tx = session.beginTransaction();
		// session.save(order);
		this.getHibernateTemplate().save(order);
		// tx.commit();
		// DBHelper.closeSession();
	}

	// 按用户ID查询
	public PageBean<Order> findByUid(String uid, int pc) {
		Expression expression = new Expression("uid", "=", uid);
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(expression);
		return findByCriteria(exprList, pc);
	}

	// 分页
	private PageBean<Order> findByCriteria(List<Expression> exprList,
			final int pc) {
		// Session session = DBHelper.getSession();
		// session.clear();
		// Transaction tx = session.beginTransaction();
		// 1. 得到ps
		final int ps = PageConstants.ORDER_PAGE_SIZE;
		// 2. 得到wheresql子句
		final StringBuilder wheresql = new StringBuilder(" where 1=1 ");
		final Map<String, Object> mapParams = new HashMap<String, Object>();
		for (Expression expression : exprList) {
			wheresql.append("and " + expression.getName())
					.append(" " + expression.getOperator()).append(" ");
			if (!expression.getOperator().equals("is null")) {
				wheresql.append(" :" + expression.getName());
				mapParams.put(expression.getName(), expression.getValue());
			}
		}
		final String hql = "select count(*) from Order " + wheresql;
		// 3.查询总计录数
		// ！！！注意第三个参数要转成数组传过去，否则会出错，number一直得到0页
		Number number = (Number) this.getHibernateTemplate().execute(
				new HibernateCallback<Number>() {

					@Override
					public Number doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query1 = session.createQuery(hql);
						query1.setProperties(mapParams);
						Number number = (Number) query1.uniqueResult();
						return number;
					}
				});
		int tr = number.intValue();
		// 4得到分页的记录select * from T_Book where 1=1 and author like '%java' order
		List<Order> beanList = this.getHibernateTemplate().execute(
				new HibernateCallback<List<Order>>() {

					@Override
					public List<Order> doInHibernate(Session session)
							throws HibernateException, SQLException {
						String allsql = "from Order " + wheresql
								+ " order by ordertime desc";
						Query query2 = session.createQuery(allsql);
						query2.setProperties(mapParams);
						// 当前页首行记录的下标
						query2.setFirstResult((pc - 1) * ps);
						query2.setMaxResults(ps);
						// 所有的订单
						List<Order> beanList = query2.list();
						return beanList;
					}

				});
		// 5生成PageBean
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPc(pc);
		pageBean.setTr(tr);
		pageBean.setPs(ps);
		// 总页数
		pageBean.setBeanList(beanList);
		// tx.commit();
		// DBHelper.closeSession();
		return pageBean;
	}

	// 为后台使用状态来查询订单而设置的分页方法，通过status来查询
	private PageBean<Order> findOrderByStatus(int status, final int pc) {
		// Session session = DBHelper.getSession();
		// session.clear();
		// Transaction tx = session.beginTransaction();
		// 1. 得到ps
		final int ps = PageConstants.ORDER_PAGE_SIZE;
		// 2. 得到wheresql子句
		final StringBuilder wheresql = new StringBuilder("where status = "
				+ status);
		String hql = "select count(*) from Order " + wheresql;
		// 3.查询总计录数
		// ！！！注意第三个参数要转成数组传过去，否则会出错，number一直得到0页
		// Query query1 = session.createQuery(hql);
		// Number number = (Number) query1.uniqueResult();
		Number number = (Number) this.getHibernateTemplate().find(hql).size();
		int tr = number.intValue();
		// 4得到分页的记录select * from T_Book where 1=1 and author like '%java' order
		List<Order> beanList = this.getHibernateTemplate().execute(
				new HibernateCallback<List<Order>>() {

					@Override
					public List<Order> doInHibernate(Session session)
							throws HibernateException, SQLException {
						String allsql = "from Order " + wheresql
								+ " order by ordertime desc";
						Query query2 = session.createQuery(allsql);
						// 当前页首行记录的下标
						// Hibernate的分页
						query2.setFirstResult((pc - 1) * ps);
						query2.setMaxResults(ps);
						List<Order> beanList = query2.list();
						return beanList;
					}

				});
		// 5生成PageBean
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPc(pc);
		pageBean.setTr(tr);
		pageBean.setPs(ps);
		// 总页数
		pageBean.setBeanList(beanList);
		// tx.commit();
		// DBHelper.closeSession();
		return pageBean;
	}
}
