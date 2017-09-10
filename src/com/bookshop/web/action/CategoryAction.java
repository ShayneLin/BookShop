package com.bookshop.web.action;

import java.util.List;

import com.bookshop.domain.Category;
import com.bookshop.service.CategoryService;
import com.bookshop.utils.BeansHelper;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends BaseAction implements ModelDriven<Category> {
	private Category category = new Category();
	private CategoryService categoryService = (CategoryService) BeansHelper
			.getBeanService("categoryService");// new CategoryService();

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	// 显示所有的分类
	public String findAll() {
		// 通过service得到所有的分类
		List<Category> parents = categoryService.findAll();
		// 保存到request中，转发到left.jsp
		request.setAttribute("parents", parents);
		return "showcategory";
	}

}
