package com.bookshop.web.action;

import java.io.UnsupportedEncodingException;

import com.bookshop.domain.Book;
import com.bookshop.pager.PageBean;
import com.bookshop.service.BookService;
import com.bookshop.utils.BeansHelper;
import com.opensymphony.xwork2.ModelDriven;

public class BookAction extends BaseAction implements ModelDriven<Book> {
	private Book book = new Book();
	private BookService bookService = (BookService) BeansHelper
			.getBeanService("bookService");//new BookService();

	
	//用于获取参数
	@Override
	public Book getModel() {
		// TODO Auto-generated method stub
		return book;
	}

	// 1.按照分类来查询

	public String findByCategory() {

		// 1.获取参数
		int pc = getPc();// 请求的页
		String cid = request.getParameter("cid");
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "listbook";
	}

	// 按照书名字来查询
	public String findByBname() throws UnsupportedEncodingException {
		// 1.获取参数
		request.setCharacterEncoding("UTF-8");
		int pc = getPc();// 请求的页
		String bname = request.getParameter("bname");
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByBookName(bname, pc);
		pb.setUrl(url);
		if (pb.getTp() == 0) {// 说明没有查找到
			pb.setPc(0);
		}
		request.setAttribute("pb", pb);
		return "listbook";
	}

	// 按照出版社来查询
	public String findByPress() {
		System.out.println("findByAuthor请求的Url：" + request.getRequestURL());
		// 1.获取参数
		int pc = getPc();// 请求的页
		String press = request.getParameter("press");
		System.out.println("press:" + press);
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByPress(press, pc);
		// 3.设置PageBean并返回
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "listbook";
	}

	// 按照作者来查询
	public String findByAuthor() throws UnsupportedEncodingException {
		// 1.获取参数
		int pc = getPc();// 请求的页
		String author = request.getParameter("author");
		// 参数转码
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		// 3.设置PageBean并返回
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "listbook";
	}

	// 复合查询
	public String findByCombination() {
		// 1.获取参数
		int pc = getPc();// 请求的页
		//book直接用ModelDriven的接口生成的book对象就行了
		String url = getUrl();
		// 2调用service获取数据
		PageBean<Book> pb = bookService.findByCombination(book, pc);
		if (pb.getTp() == 0) {// 说明没有查找到
			pb.setPc(0);
		}
		// 3.设置PageBean并返回
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "listbook";
	}

	// 根据bid返回一本图书，给用户显示该书的详情
	// 复合查询
	public String load() {
		// 1.获取参数
		String bid = request.getParameter("bid");
		Book book = bookService.findBookByBid(bid);
		request.setAttribute("book", book);
		// 跳到显示详情的页面
		return "load";
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
