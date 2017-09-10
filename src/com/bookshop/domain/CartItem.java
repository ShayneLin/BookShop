package com.bookshop.domain;

public class CartItem {
	private String cartItemId;// 主键
	private int quantity;// 数
	private String bid;
	private int orderBy;
	private Book book;// 用于存储bid
	private User user;// 所属用户

	// 得到小计
	public Double getSubtotal() {
		Double subTotal = book.getCurrPrice() * quantity;
		return Math.floor(subTotal);
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", quantity=" + quantity
				+ "]";
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

}
