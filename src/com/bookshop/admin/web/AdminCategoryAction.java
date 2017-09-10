package com.bookshop.admin.web;

import java.util.List;

import com.bookshop.domain.Category;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.utils.BeansHelper;
import com.bookshop.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends BaseAction implements
		ModelDriven<Category> {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService = (CategoryService) BeansHelper.getBeanService("categoryService") ;//= new CategoryService();
	// 删除二级分类的是有要跨模块调用BookService
	private BookService bookService = (BookService) BeansHelper.getBeanService("bookService");// new BookService();
	private Category category;

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		if (category == null) {
			category = new Category();
		}
		return category;
	}

	// 查找所有的分类，用于前台显示
	public String findAll() {
		// 1.查找所有的分类
		List<Category> categoryList = categoryService.findAll();
		// 2.存储到req，转发到/adminjsps/admin/category/list.jsp
		request.setAttribute("categoryList", categoryList);
		return "booklist";
	}

	// 删除父分类
	public String deleteParent() {
		// 1.获取参数cid
		String cid = request.getParameter("cid");
		int num = categoryService.findChildNumByParents(cid);
		if (num != 0) {
			request.setAttribute("msg", "该分类下还有子分类，不能删除！");
			return "msg";
		}
		// 删除分类
		categoryService.deleteCategory(cid);
		return findAll();
	}

	// 删除子分类
	public String deleteChild() {
		// 1.获取参数cid
		String cid = request.getParameter("cid");
		// 跨模块调用
		int num = bookService.findBookNumByCid(cid);
		if (num != 0) {
			request.setAttribute("msg", "该分类下还有图书，不能删除！");
			return "msg";
		}
		// 删除分类
		categoryService.deleteCategory(cid);
		return findAll();
	}

	// 增加父分类和子分类的方法通用的
	public String add() {
		// 1.获取参数，生成Category
		// 有ModelDriven帮我们封装
		String pid = request.getParameter("pid");
		// 2.调用service的add方法,转发到list.jsp
		categoryService.add(category,pid);
		return findAll();
	}

	// 增加子分类的第一步
	public String addChildPre() {
		// 1.获取当前的子分类的pid,用于默认显示的选项
		String pid = request.getParameter("pid");
		List<Category> parents = categoryService.findAllParents();
		// 2设置到req中，转发到/adminjsps/admin/category/add2.jsp
		request.setAttribute("pid", pid);
		request.setAttribute("parents", parents);
		return "addpre";
	}

	// 修改Parnet分类前的操作
	public String editParentPre() {
		// 1.获取当前的分类的cid,通过cid加载Category
		String cid = request.getParameter("cid");
		Category category = categoryService.loadCategory(cid);
		// 2设置到req中，转发到/adminjsps/admin/category/edit.jsp
		request.setAttribute("category", category);
		return "editparentpre";
	}

	// 修改父分类
	public String editParent() {
		categoryService.editCategory(category,null);
		return findAll();
	}

	// 修改子分类前的操作
	public String editChildPre() {
		// 1.获取当前的分类的cid,通过cid加载Category
		String cid = request.getParameter("cid");
		Category category = categoryService.loadCategory(cid);
		// 还要查询所有的一级分类
		List<Category> parents = categoryService.findAllParents();
		// 2设置到req中，转发到/adminjsps/admin/category/edit2.jsp
		request.setAttribute("category", category);
		request.setAttribute("pid", category.getParent().getCid());
		request.setAttribute("parents", parents);
		return "editchildpre";
	}

	// 修改子分类
	public String editChild() {
		// // 注意，子分类还有pid的
		String pid = request.getParameter("pid");
		categoryService.editCategory(category,pid);
		return findAll();
	}

}
