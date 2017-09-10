package com.bookshop.admin.service;

import java.sql.SQLException;

import com.bookshop.admin.dao.AdminDao;
import com.bookshop.admin.domain.Admin;

public class AdminService {
	private AdminDao adminDao;// = new AdminDao();

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	// 查找Admin
	public Admin findAdminByNameAndPwd(Admin form) {

		try {
			Admin admin = adminDao.findAdminByNameAndPwd(form.getAdminname(),
					form.getAdminpwd());
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}
	}
}
