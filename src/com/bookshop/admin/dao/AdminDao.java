package com.bookshop.admin.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bookshop.admin.domain.Admin;

public class AdminDao extends HibernateDaoSupport {

	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 查找Admin
	public Admin findAdminByNameAndPwd(String adminname, String adminpwd)
			throws SQLException {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String hql = "from Admin where adminname = ? and adminpwd = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, adminname);
		// query.setParameter(1, adminpwd);
		// Admin admin = (Admin) query.uniqueResult();
		// tx.commit();
		// DBHelper.closeSession();
		Admin admin = null;
		List list = this.getHibernateTemplate().find(hql,
				new Object[] { adminname, adminpwd });
		if (list.size() > 0)
			admin = (Admin) list.get(0);
		return admin;
	}
}
