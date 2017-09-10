package com.bookshop.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bookshop.domain.User;
import com.bookshop.utils.DBHelper;

public class UserDao extends HibernateDaoSupport {

	public void setMySessionFactory(SessionFactory sessionFactory) {
		System.out.println("hahah" + sessionFactory);
		super.setSessionFactory(sessionFactory);
	}

	// 根据uuid和password查询原密码是否正确
	public User findByUuidAndPass(final String uid, final String loginpass) {
		User user = this.getHibernateTemplate().execute(
				new HibernateCallback<User>() {

					@Override
					public User doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from User where uid = ? and loginpass = ?";
						Query query = session.createQuery(hql);
						query.setParameter(0, uid);
						query.setParameter(1, loginpass);
						User user = (User) query.uniqueResult();
						DBHelper.closeSession();
						System.out.println("根据ID查找的User：" + user);
						return user;
					}
				});
		return user;
	}

	// 更新密码
	public void updateLoginpass(User user, String newpass) {
		user.setLoginpass(newpass);
		this.getHibernateTemplate().update(user);
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		// user.setLoginpass(newpass);
		// session.update(user);
		// tx.commit();
		// DBHelper.closeSession();
	}

	// 通过用户和密码查找用户
	public User findByLoginnameAndPass(final String loginname,
			final String password) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		User user = (User) this.getHibernateTemplate().execute(
				new HibernateCallback<User>() {

					@Override
					public User doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from User where loginname=? and loginpass = ?";
						Query query = session.createQuery(hql);
						query.setString(0, loginname);
						query.setString(1, password);
						User user = (User) query.uniqueResult();
						return user;
					}
				});
		// User user = (User) query.uniqueResult();
		// tx.commit();
		// DBHelper.closeSession();
		return user;

	}

	// 通过激活码查询用户
	public User findByActivication(final String code) {
		User user = (User) this.getHibernateTemplate().execute(
				new HibernateCallback<User>() {

					@Override
					public User doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from User where activationCode=?";
						Query query = session.createQuery(hql);
						query.setString(0, code);
						User user = (User) query.uniqueResult();
						return user;
					}
				});

		// Session session = DBHelper.getSession();
		// Query query = session.createQuery(hql);
		// query.setString(0, code);
		// User user = (User) query.uniqueResult();
		return user;

	}

	// 更新激活状态
	public void updateStatus(final String uid, final boolean status) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "update T_User set status=? where uid=?";
				Query query = session.createSQLQuery(sql);
				query.setParameter(0, status);
				query.setParameter(1, uid);
				query.executeUpdate();
				return null;
			}
		});
		// String sql = "update T_User set status=? where uid=?";
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		// Query query = session.createSQLQuery(sql);
		// query.setParameter(0, status);
		// query.setParameter(1, uid);
		// query.executeUpdate();
		// tx.commit();
		// DBHelper.closeSession();
	}

	// 验证用户名
	public boolean ajaxValidateLoginname(final String loginname) {
		final String hql = "select count(*) from User where loginname = ?";
		// List list = this.getHibernateTemplate().find(hql, loginname);
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		//
		Number number = this.getHibernateTemplate().execute(
				new HibernateCallback<Number>() {

					@Override
					public Number doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setString(0, loginname);
						Number num = (Number) query.uniqueResult();
						return num;
					}
				});
		// tx.commit();
		// DBHelper.closeSession();
		// System.out.println("num=" + num);
		// System.out.println("num.intValue() != 0:" + (num.intValue() != 0));
		return number.intValue() != 0;
	}

	// 验证邮箱
	public boolean ajaxValidateEmail(final String email) {
		final String hql = "select count(*) from User where email = ?";
		// List list = this.getHibernateTemplate().find(hql, email);
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		Number number = this.getHibernateTemplate().execute(
				new HibernateCallback<Number>() {

					@Override
					public Number doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setString(0, email);
						Number num = (Number) query.uniqueResult();
						return num;
					}
				});
		// tx.commit();
		// DBHelper.closeSession();
		// System.out.println("num=" + num);
		// System.out.println("num.intValue() != 0:" + (num.intValue() != 0));
		return number.intValue() != 0;
	}

	// 添加用
	public void add(User user) {
		this.getHibernateTemplate().save(user);
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		// session.save(user);
		// tx.commit();
		// DBHelper.closeSession();
	}

}
