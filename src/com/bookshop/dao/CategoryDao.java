package com.bookshop.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bookshop.domain.Category;
import com.bookshop.utils.DBHelper;

public class CategoryDao extends HibernateDaoSupport {

	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 查询指定父分类下的子分类数
	public int findChildNumByParents(final String cid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		final String hql = "select count(*) from Category where pid = ?";
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

	// 删除分类
	public void deleteCategory(final String cid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		final String sql = "delete from T_Category where cid = ?";
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createSQLQuery(sql);
				query.setParameter(0, cid);
				query.executeUpdate();
				return null;
			}
		});
		// tx.commit();
		// DBHelper.closeSession();
	}

	// 根据cid查询分类点的详细信息
	public Category loadCategory(String cid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String hql = "from Category where cid = ?";
		Category category = null;
		List list = this.getHibernateTemplate().find(hql, cid);
		if (list.size() > 0)
			category = (Category) list.get(0);
		// Query query = session.createQuery(hql);
		// query.setParameter(0, cid);
		// Category category = (Category) query.uniqueResult();
		// tx.commit();
		// DBHelper.closeSession();
		return category;

	}

	// 修改分类
	public void editCategory(final Category category, final String pid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cname", category.getCname());
				map.put("desc", category.getDesc());
				map.put("cid", category.getCid());
				String sql = "update T_Category set cname = :cname, `desc` = :desc where cid = :cid";
				if (pid != null) {
					sql = "update T_Category set cname = :cname, pid = :pid,`desc` = :desc where cid = :cid";
					map.put("pid", pid);
				}
				Query query = session.createSQLQuery(sql);
				query.setProperties(map);
				query.executeUpdate();
				return null;
			}

		});

		// tx.commit();
		// DBHelper.closeSession();

	}

	// Admin的查找父分类的方法
	public List<Category> findAllParents() {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String hql = "from Category where pid is null order by orderBy";
		// Query query = session.createQuery(hql);
		// 转成List<Category>形式
		// List<Category> categories = query.list();// toListCategory(list);
		// tx.commit();
		// DBHelper.closeSession();
		List<Category> categories = this.getHibernateTemplate().find(hql);
		return categories;
	}

	// Admin的查找父分类下的所有子分类(用于异步请求子分类里，addBook）
	public List<Category> findAllChilds(String pid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String hql = "from Category where pid = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, pid);
		// 转成List<Category>形式
		// List<Category> categories = query.list();// toListCategory(list);
		// tx.commit();
		// DBHelper.closeSession();
		List<Category> categories = this.getHibernateTemplate().find(hql, pid);
		return categories;
	}

	// 管理员的Add方法,通用方法
	public void add(Category category, String pid) {
		Category parent = null;
		// 有父分类时
		if (pid != null) {
			parent = loadCategory(pid);
		}
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		category.setParent(parent);
		this.getHibernateTemplate().save(category);
		// session.save(category);
		// tx.commit();
		// DBHelper.closeSession();
	}

	// 查找所有的分类
	public List<Category> findAll() {
		String hql = "from Category where pid is null order by orderBy";
		// Session session = DBHelper.getSession();
		// session.clear();
		// Transaction tx = session.beginTransaction();
		// List<Category> catgy = session.createQuery(hql).list();
		// tx.commit();
		// DBHelper.closeSession();
		List<Category> catgy = this.getHibernateTemplate().find(hql);
		return catgy;
	}

	// 查询子分类
	public List<Category> findByParent(String pid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String hql = "from Category where pid = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, pid);
		// Category category = (Category) query.uniqueResult();
		Category category = (Category) this.getHibernateTemplate()
				.find(hql, pid).get(0);
		// 找到子分类
		List<Category> list = listToSet(category.getChildren());
		// tx.commit();
		// DBHelper.closeSession();
		return list;
	}

	// Set转成List
	private List<Category> listToSet(Set<Category> children) {
		List<Category> list = new ArrayList<Category>();
		Iterator it = children.iterator();
		while (it.hasNext()) {
			Category category = (Category) it.next();
			list.add(category);
		}

		return list;
	}
}
