package com.bookshop.admin.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.domain.Book;
import com.bookshop.domain.Category;
import com.bookshop.pager.PageBean;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.utils.BeansHelper;
import com.bookshop.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class AdminBookAction extends BaseAction implements ModelDriven<Book> {
	private BookService bookService = (BookService) BeansHelper
			.getBeanService("bookService");// new
	// BookService();
	private CategoryService categoryService = (CategoryService) BeansHelper
			.getBeanService("categoryService");// new CategoryService();
	private Book book;

	@Override
	public Book getModel() {
		if (book == null) {
			book = new Book();
		}
		return book;
	}

	// 编辑图书的第一步,加载图书
	public String load() {
		/*
		 * 1. 获取bid，得到Book对象，保存之
		 */
		String bid = request.getParameter("bid");
		Book book = bookService.loadBookByBid(bid);
		request.setAttribute("book", book);
		// 2. 获取所有一级分类，保存之
		request.setAttribute("parents", categoryService.findAllParents());
		// 3. 获取当前图书所属的一级分类下所有2级分类
		String pid = book.getCategory().getParent().getCid();
		request.setAttribute("children", categoryService.findAllChilds(pid));
		// 4. 转发到desc.jsp显示
		return "bookdesc";
	}

	public String editOrDelete() {
		String method = request.getParameter("method");
		if (method.trim().equals("delete")) {
			System.out.println("您选择了删除");
			return delete();
		} else if (method.trim().equals("edit")) {
			System.out.println("您选择了编辑");
			return edit();
		}
		return "msg";

	}

	// 编辑图书
	public String edit() {
		// ModelDriven已经封装好了数据
		String cid = request.getParameter("cid");
		bookService.edit(book, cid);
		request.setAttribute("msg", "修改图书成功！");
		return "msg";
	}

	// 删除图书
	public String delete() {
		String bid = request.getParameter("bid");
		// 删除图片,先删除图片文件在删除记录
		Book book = bookService.findBookByBid(bid);
		String savepath = request.getServletContext().getRealPath("/");// 获取真实的路径
		new File(savepath, book.getImage_w()).delete();// 删除文件
		new File(savepath, book.getImage_b()).delete();// 删除文件
		bookService.delete(bid);// 删除数据库的记录
		request.setAttribute("msg", "删除图书成功！");
		return "msg";
	}

	// 添加图书的第一步,查找所有的父分类
	public String addPre() {
		List<Category> parents = categoryService.findAllParents();
		request.setAttribute("parents", parents);
		return "addpre";
	}

	// 异步请求查找子分类
	public String ajaxFindChildren() throws IOException {
		String pid = request.getParameter("pid");
		// 通过categoryService查找对应的子分类
		List<Category> list = categoryService.findAllChilds(pid);
		// 封装成Json格式的数据
		String json = toJsonArray(list);
		// 写出
		response.getWriter().print(json);
		return null;
	}

	// 生成JsonArray格式的字符串
	private String toJsonArray(List<Category> list) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < list.size(); i++) {
			sb.append(toJson(list.get(i)));
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// 生成Json对象// {"cid":"fdsafdsa", "cname":"fdsafdas"}
	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"cid\"").append(":").append("\"").append(category.getCid())
				.append("\"");
		sb.append(",");
		sb.append("\"cname\"").append(":").append("\"")
				.append(category.getCname()).append("\"");
		sb.append("}");
		return sb.toString();
	}

	// 通过categoryServlet来查询所有的一级分类和二级分类的详细信息
	public String findCategoryAll() {

		// 查找所有的类别
		List<Category> categoryList = categoryService.findAll();
		request.setAttribute("categoryList", categoryList);
		// 放入req中,转发到/adminjsps/admin/book/left.jsp
		return "showcategory";
	}

	// 按照分类来查询

	public String findByCategory() {
		// 1.获取参数
		int pc = getPc();// 请求的页
		String cid = request.getParameter("cid");
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "booklist";
	}

	// 按照书名字来查询
	public String findByBname() {
		// 1.获取参数
		int pc = getPc();// 请求的页
		String bname = request.getParameter("bname");
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByBookName(bname, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "booklist";
	}

	// 按照出版社来查询
	public String findByPress() {
		// 1.获取参数
		int pc = getPc();// 请求的页
		String press = request.getParameter("press");
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByPress(press, pc);
		// 3.设置PageBean并返回
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "booklist";
	}

	// 按照作者来查询
	public String findByAuthor() throws UnsupportedEncodingException {
		// 1.获取参数
		int pc = getPc();// 请求的页
		String author = request.getParameter("author");
		// 参数转码
		author = new String(author.getBytes("ISO-8859-1"), "UTF-8");
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByAuthor("明日科技", pc);
		// 3.设置PageBean并返回
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "booklist";
	}

	// 复合查询
	public String findByCombination() {
		// 1.获取参数
		int pc = getPc();// 请求的页
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByCombination(book, pc);
		// 3.设置PageBean并返回
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "booklist";
	}

	// 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	public String getUrl() {
		// 到/项目名/***部分的URI
		String url = request.getRequestURI();
		// 得到查询的字符串，不包括？
		url = url + "?" + request.getQueryString();
		// 还要去掉原来的&cid=***字串
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			// 坑死了，要重新接收该url，因为调用substring()方法后重新生成了String对象
			url = url.substring(0, index);
		}
		return url;

	}

	// 获取页号，刚开始默认为 1
	private int getPc() {
		int pc = 1;
		String param = request.getParameter("pc");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}

}
