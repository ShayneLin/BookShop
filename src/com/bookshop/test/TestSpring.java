package com.bookshop.test;

import com.bookshop.service.UserService;
import com.bookshop.service.exception.UserException;
import com.bookshop.utils.BeansHelper;
import com.bookshop.web.form.UserForm;

public class TestSpring {
	public static void main(String args[]) throws UserException {
		// testUser();
		updateUserPass();
	}

	public static void testUser() {

		UserService userService = (UserService) BeansHelper
				.getBeanService("userService");
		UserForm userForm = new UserForm();
		userForm.setLoginname("shunzi");
		userForm.setLoginpass("admin");
		UserForm user = userService.login(userForm);
		System.out.println(user);
	}

	public static void updateUserPass() throws UserException {
		UserService userService = (UserService) BeansHelper
				.getBeanService("userService");
		UserForm userForm = new UserForm();
		userForm.setLoginname("shunzi");
		userForm.setLoginpass("admin");
		UserForm user = userService.login(userForm);
		userService.updateLoginpass(user.getUid(), "haha", "admin");

	}
}
