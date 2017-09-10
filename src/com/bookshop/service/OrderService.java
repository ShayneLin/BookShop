package com.bookshop.service;

import com.bookshop.dao.OrderDao;
import com.bookshop.domain.Order;
import com.bookshop.pager.PageBean;

public class OrderService {
	private OrderDao orderDao;// = new OrderDao();

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// Admin查询所有,要rollback
	public PageBean<Order> findAll(int pc) {
		PageBean<Order> pb = orderDao.findAll(pc);
		return pb;
	}

	// 按照状态查找,要Rollback
	public PageBean<Order> findByStatus(int status, int pc) {
		PageBean<Order> pb = orderDao.findByStatus(status, pc);
		return pb;
	}

	// ----------------------->

	// 查找对应订单的状态
	public int findOrderStatus(String oid) {
		return orderDao.findOrderStatus(oid);
	}

	// 修改对应订单的状态
	public void updateOrderStatus(String oid, int status) {
		orderDao.updateOrderStatus(oid, status);
	}

	// 加载订单号的对应的详细信息
	public Order loadOrderByOid(String oid) {
		Order order = orderDao.loadOrderByOid(oid);
		return order;

	}

	// 提交创建订单
	public void creatOrder(Order order) {
		orderDao.createOrder(order);
	}

	// 显示我的订单
	public PageBean<Order> myOrders(String uid, int pc) {
		PageBean<Order> pageBean = orderDao.findByUid(uid, pc);
		return pageBean;
	}
}
