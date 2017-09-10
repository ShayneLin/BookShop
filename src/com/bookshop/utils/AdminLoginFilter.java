package com.bookshop.utils;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AdminLoginFilter extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		ActionContext ctx = ai.getInvocationContext();
		Map<String, Object> session = ctx.getSession();
		Object user = session.get("sessionUser");
		System.out.println("LoginFilter：" + user);
		if (user == null) {
			return "needlogin";
		}
		return ai.invoke();
	}
}
