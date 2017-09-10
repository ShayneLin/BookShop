package com.bookshop.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BeansHelper {
	public static ApplicationContext ac;
	static {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	public static Object getBeanService(String beanName) {
		Object bean = null;
		bean = ac.getBean(beanName);
		return bean;
	}
}
