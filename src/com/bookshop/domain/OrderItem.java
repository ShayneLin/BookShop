package com.bookshop.domain;


//与Order双相关联
public class OrderItem {
	private String orderItemId;// 主键
	private int quantity;// 数量
	private double subtotal;// 小计
	private String bname;
	private double currPrice;
	private String image_b;
	private Book book;// 所关联的Book
	private Order order;// 所属的订单
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(double d) {
		this.currPrice = d;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", quantity="
				+ quantity + ", subtotal=" + subtotal + "]";
	}
	
	
	
}
