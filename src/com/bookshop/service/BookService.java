package com.bookshop.service;

import com.bookshop.dao.BookDao;
import com.bookshop.domain.Book;
import com.bookshop.pager.PageBean;

public class BookService {
	private BookDao bookDao;// = new BookDao();

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	// Admin的add图书功能
	public void add(Book book) {
		bookDao.add(book);
	}

	// Admin通过分类的cid查找该分类下的图书的数量(用于删除二级分类)
	public int findBookNumByCid(String cid) {
		return bookDao.findBookNumByCid(cid);
	}

	public Book loadBookByBid(String bid) {
		return bookDao.loadBookByBid(bid);
	}

	// 编辑图书
	public void edit(Book book, String cid) {
		bookDao.edit(book, cid);
	}

	// 删除图书
	public void delete(String bid) {
		bookDao.delete(bid);

	}

	// ------------------------------------------------->

	// 根据bid返回一本书，给用户显示该书的详情
	public Book findBookByBid(String bid) {
		return bookDao.findBookByBid(bid);
	}

	// 按照书名查询
	public PageBean<Book> findByBookName(String bname, int pc) {
		return bookDao.findByBookName(bname, pc);

	}

	// 按照分类来查询
	public PageBean<Book> findByCategory(String cid, int pc) {
		return bookDao.findByCategory(cid, pc);
	}

	// 按照出版社来查询
	public PageBean<Book> findByPress(String press, int pc) {
		return bookDao.findByPress(press, pc);
	}

	// 按照作者来查询
	public PageBean<Book> findByAuthor(String bauthor, int pc) {
		return bookDao.findByAuthor(bauthor, pc);

	}

	// 复合查询
	public PageBean<Book> findByCombination(Book book, int pc) {
		return bookDao.findByCombination(book, pc);
	}

}
