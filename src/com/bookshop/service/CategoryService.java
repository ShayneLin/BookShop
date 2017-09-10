package com.bookshop.service;

import java.util.List;

import com.bookshop.dao.CategoryDao;
import com.bookshop.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao;// = new CategoryDao();

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// Admin查询指定父分类下的子分类数
	public int findChildNumByParents(String cid) {
		return categoryDao.findChildNumByParents(cid);
	}

	// Admin删除分类
	public void deleteCategory(String cid) {
		categoryDao.deleteCategory(cid);
	}

	// Admin根据cid查询分类点的详细信息
	public Category loadCategory(String cid) {
		return categoryDao.loadCategory(cid);
	}

	// Admin修改分类
	public void editCategory(Category category, String pid) {
		categoryDao.editCategory(category, pid);
	}

	// Admin的查找父分类的方法
	public List<Category> findAllParents() {
		return categoryDao.findAllParents();
	}

	public List<Category> findAllChilds(String pid) {
		return categoryDao.findAllChilds(pid);
	}

	// 管理员的Add方法,通用方法
	public void add(Category category, String pid) {
		categoryDao.add(category, pid);
	}

	// 查找所有分类
	public List<Category> findAll() {
		List<Category> categories = categoryDao.findAll();
		return categories;
	}
}
