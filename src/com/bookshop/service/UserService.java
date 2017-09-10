package com.bookshop.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import com.bookshop.dao.UserDao;
import com.bookshop.domain.User;
import com.bookshop.service.exception.UserException;
import com.bookshop.utils.CommonUtils;
import com.bookshop.utils.mail.Mail;
import com.bookshop.utils.mail.MailUtils;
import com.bookshop.web.form.UserForm;

public class UserService {
	private UserDao userDao;// = new UserDao();

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 更新密码
	public boolean updateLoginpass(String uid, String newpass, String oldpass)
			throws UserException {
		// 先查找原密码是否正确
		User user = userDao.findByUuidAndPass(uid, oldpass);
		if (user == null) {// 说明密码错误
			throw new UserException("原密码错误");
		}
		// 否则，更新密码
		userDao.updateLoginpass(user, newpass);
		return false;
	}

	// 验证用户名
	public boolean ajaxValidateLoginname(String loginname) {
		boolean bool = false;

		bool = userDao.ajaxValidateLoginname(loginname);
		return bool;
	}

	// 验证密码
	public boolean ajaxValidateEmail(String email) {
		boolean bool = false;
		bool = userDao.ajaxValidateEmail(email);
		return bool;
	}

	// 注册
	public void regist(UserForm userForm) {
		// 转换数据VO-PO,补齐数据
		User user = convertVoToPo(userForm);
		userDao.add(user);
		// 生成邮件并且发出邮件
		// 读取配置文件properties
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream("email_template.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 登录邮件服务器，得到session
		String host = prop.getProperty("host");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		prop.put("mail.smtp.auth", "true");
		Session session = MailUtils.createSession(host, username, password);

		// 创建Mail对象
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		// 填充占位符
		String content = MessageFormat.format(prop.getProperty("content"),
				user.getActivationCode());
		Mail mail = new Mail(from, to, subject, content);
		// 发送邮件
//		try {
//		//	MailUtils.send(session, mail);
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}

	public UserForm login(UserForm userForm) {
		User user = null;
		user = userDao.findByLoginnameAndPass(userForm.getLoginname(),
				userForm.getLoginpass());
		return convertPoToVo(user);
	}

	// 将PO对象转为VO对象
	private UserForm convertPoToVo(User user) {
		UserForm userForm = null;
		if (user == null)
			return userForm;
		userForm = new UserForm();
		// 转换
		userForm.setUid(user.getUid());
		userForm.setLoginname(user.getLoginname());
		userForm.setStatus(user.isStatus());
		return userForm;
	}

	public void activatioin(String code) throws UserException {
		// 1. 通过激活码查询用户 2. 如果User为null，说明是无效激活码，抛出异常，给出异常信息（无效激活码） 3.
		// 查看用户状态是否为true，如果为true，抛出异常，给出异常信息（请不要二次激活） 4. 修改用户状态为true
		User user = userDao.findByActivication(code);
		if (user == null)
			throw new UserException("无效的激活码！");
		if (user.isStatus())
			throw new UserException("您已经激活过了，不要二次激活！");
		userDao.updateStatus(user.getUid(), true);// 修改状态
	}

	// 将UserForm封装成PO对象的User
	private User convertVoToPo(UserForm userForm) {
		User user = new User();
		// 设置值
		user.setLoginname(userForm.getLoginname());
		user.setLoginpass(userForm.getLoginpass());
		user.setEmail(userForm.getEmail());
		user.setStatus(false);
		user.setActivationCode(CommonUtils.uuid() + CommonUtils.uuid());
		return user;

	}

}
