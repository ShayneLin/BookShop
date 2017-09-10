package com.bookshop.admin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.admin.domain.Admin;
import com.bookshop.admin.service.AdminService;
import com.bookshop.utils.BeansHelper;
import com.bookshop.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends BaseAction implements ModelDriven<Admin> {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = (AdminService) BeansHelper.getBeanService("adminService");//new AdminService();
	private Admin admin;

	@Override
	public Admin getModel() {
		if (admin == null) {
			admin = new Admin();
		}
		return admin;
	}

	// 管理员的登录
	public String login() {
		// 1获取参数
		// 查找该用户是否正确且存在
		Admin form = adminService.findAdminByNameAndPwd(admin);
		System.out.println("admin=" + admin);
		if (form != null) {
			request.getSession().setAttribute("sessionUser", form);
			System.out.println("进来了");
			return "login";
		}
		// 否则不存在
		request.setAttribute("msg", "用户名或密码不正确!");
		return "error";
	}

	public String quit() {
		HttpSession session = request.getSession();
		session.invalidate();
		return "quit";
	}

}
