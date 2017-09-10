package com.bookshop.domain;

import java.util.Set;


//与OrderItem双相关联
public class Order {
	private String oid;// 主键
	private String uid;// 拥有者
	private String ordertime;// 下单时间
	private Double total;// 总价
	private Integer status;// 订单状态：1未付款, 2已付款但未发货, 3已发货未确认收货, 4确认收货了交易成功,
						// 5已取消(只有未付款才能取消)
	private String address;// 收货地址
	private User owner;// 订单的所有者

	private Set<OrderItem> orderItemList;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(Set<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", ordertime="
				+ ordertime + ", total=" + total + ", status=" + status
				+ ", address=" + address + "]";
	}

}
