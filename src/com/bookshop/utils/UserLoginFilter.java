package com.bookshop.utils;

import java.util.Map;

import com.bookshop.admin.domain.Admin;
import com.bookshop.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class UserLoginFilter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		ActionContext ctx = ai.getInvocationContext();
		Map<String, Object> session = ctx.getSession();
		Object user = session.get("sessionUser");
		System.out.println("LoginFilter：" + user);
		Map<String, Object> request = ctx.getParameters();
		if (user == null) {
			request.put("msg", "您还没有登录!");
			return "needlogin";
		}
		return ai.invoke();
	}
}
