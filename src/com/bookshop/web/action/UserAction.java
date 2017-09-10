package com.bookshop.web.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.bookshop.service.UserService;
import com.bookshop.service.exception.UserException;
import com.bookshop.utils.BeansHelper;
import com.bookshop.web.form.UserForm;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends BaseAction implements ModelDriven<UserForm> {
	// 1.User对象，接收注册的数据
	private UserForm userForm = new UserForm();
	// 2.service对象
	private UserService userService = (UserService) BeansHelper.getBeanService("userService");//= new UserService();

	@Override
	public UserForm getModel() {
		return userForm;
	}

	// 1.注册的功能
	public String regist() {
		System.out.println(userForm);
		// 验证数据的正确性
		Map<String, String> errors = validateRegist(userForm,
				request.getSession());
		if (errors.size() > 0) {
			request.setAttribute("form", userForm);
			request.setAttribute("errors", errors);
			return "regist_error";
		}
		userService.regist(userForm);
		// 保存成功信息，转发到msg.jsp显示！
		request.setAttribute("code", "success");
		request.setAttribute("msg", "注册功能，请马上到邮箱激活！");
		return "regist";
	}

	// 2.登录功能
	public String login() throws UnsupportedEncodingException {
		// 得到数据库的表中的用户信息，不为null则用户存在，否则不存在
		UserForm user = userService.login(userForm);
		if (user == null) {// 不存在该用户
			// 回显信息
			request.setAttribute("user", user);
			request.setAttribute("msg", "你的用户名或密码错误");
			return "loginerror";
		} else {
			// 判断是否激活了
			if (!user.isStatus()) {// 未激活
				request.setAttribute("user", user);
				request.setAttribute("msg", "你的帐号未激活");
				return "loginerror";
			} else {// 登陆成功
				HttpSession session = request.getSession();
				session.setAttribute("sessionUser", user);
				String name = URLEncoder.encode(user.getLoginname(), "utf-8");
				Cookie cookie = new Cookie("loginname", name);
				cookie.setMaxAge(1000 * 60 * 60 * 24 * 10);
				response.addCookie(cookie);
				return "login";// 重定向到主页
			}
		}
	}

	// 3.更新密码
	public String updatePassword() {
		// 获取session中的用户数据
		UserForm user = (UserForm) request.getSession().getAttribute(
				"sessionUser");
		if (user == null) {// 说明用户未登录
			request.setAttribute("msg", "你还未登录");
			return "notlogin";

		} else {
			try {
				userService.updateLoginpass(user.getUid(),
						userForm.getNewpass(), userForm.getLoginpass());
				request.setAttribute("msg", "修改密码成功");
				request.setAttribute("code", "success");
				return "resetpassmsg";
			} catch (UserException e) {
				// 更改密码出错
				// e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
				request.setAttribute("user", userForm);// 回显
				return "resetpassmsg";
			}
		}

	}
	public String activation() {
		/*
		 * 1. 获取参数激活码
		 * 2. 用激活码调用service方法完成激活
		 *   > service方法有可能抛出异常, 把异常信息拿来，保存到request中，转发到msg.jsp显示
		 * 3. 保存成功信息到request，转发到msg.jsp显示。
		 */
		String code = request.getParameter("activationCode");
		try {
			userService.activatioin(code);
			request.setAttribute("code", "success");//通知msg.jsp显示对号
			request.setAttribute("msg", "恭喜，激活成功，请登录！");
		} catch (UserException e) {
			// 说明service抛出了异常
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("code", "error");//通知msg.jsp显示
		}
		return "msg";
	}
	// 4.退出
	public String quit() {
		// 清除sesssion
		request.getSession().invalidate();
		return "quit";

	}

	// 验证用户名
	public String ajaxValidateLoginname() throws IOException {
		// 得到用户名
		String loginname = request.getParameter("loginname");
		System.out.println("loginname=" + loginname);
		// 验证
		boolean bool = userService.ajaxValidateLoginname(loginname);
		System.out.println("bool=" + bool);
		response.getWriter().print(bool);
		return null;
	}

	// 验证Email
	public String ajaxValidateEmail() throws IOException {
		// 得到用户名
		String email = request.getParameter("email");
		// 验证
		boolean bool = userService.ajaxValidateEmail(email);
		System.out.println("action:bool=" + bool);
		response.getWriter().print(bool);
		return null;
	}

	// 验证验证码
	public String ajaxValidateVerifyCode() throws IOException {
		// 1. 获取输入框中的验证码
		String verifyCode = request.getParameter("verifyCode");
		// 2. 获取图片上真实的校验码
		String vcode = (String) request.getSession().getAttribute("vCode");
		// 3. 进行忽略大小写比较，得到结果
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		// 4. 发送给客户端
		response.getWriter().print(b);
		return null;
	}

	// 注册校验 对表单的字段进行逐个校验，如果有错误，使用当前字段名称为key，错误信息为value，保存到map中 返回map
	private Map<String, String> validateRegist(UserForm userForm,
			HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		// 校验登录名

		String loginname = userForm.getLoginname();
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空！");
		} else if (loginname.length() < 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在3~20之间！");
		} else if (userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname", "用户名已被注册！");
		}

		// 校验登录密码

		String loginpass = userForm.getLoginpass();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空！");
		} else if (loginpass.length() < 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在3~20之间！");
		}

		// 确认密码校验
		String reloginpass = userForm.getReloginpass();
		if (reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "确认密码不能为空！");
		} else if (!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "两次输入不一致！");
		}
		// 校验email
		String email = userForm.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if (!email
				.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email格式错误！");
		} else if (userService.ajaxValidateEmail(email)) {
			errors.put("email", "Email已被注册！");
		}

		// 5. 验证码校验
		String verifyCode = userForm.getVerifyCode();
		String vcode = (String) session.getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误！");
		}

		return errors;
	}

}
