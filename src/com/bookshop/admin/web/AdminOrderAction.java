package com.bookshop.admin.web;

import com.bookshop.domain.Order;
import com.bookshop.pager.PageBean;
import com.bookshop.service.OrderService;
import com.bookshop.utils.BeansHelper;
import com.bookshop.web.action.BaseAction;

public class AdminOrderAction extends BaseAction {
	private OrderService orderService =(OrderService) BeansHelper.getBeanService("orderService");// new OrderService();

	// 获取当前页码

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

	// 截取url，页面中的分页导航中需要使用它做为超链接的目标！

	private String getUrl() {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	// 查看所有订单
	public String findAll(){
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc();
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl();

		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<Order> pb = orderService.findAll(pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "orderlist";
	}

	// 按照状态分页
	public String findByStatus(){
		// 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		int pc = getPc();
		// 2. 得到url：...
		String url = getUrl();
		// 4. 使用pc和status调用service#findByStatus得到PageBean
		String number = request.getParameter("status");
		PageBean<Order> pb = orderService.findByStatus(
				Integer.parseInt(number), pc);
		// 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "orderlist";
	}

	// 加载订单的详细信息
	public String loadOrder() {
		// 1.获取订单好oid
		String oid = request.getParameter("oid");
		String btn = request.getParameter("btn");
		// 2.通过service获取订单详细信息
		Order order = orderService.loadOrderByOid(oid);

		// 3.存入请求中,并且转发
		request.setAttribute("order", order);
		request.setAttribute("btn", btn);
		return "orderdesc";
	}

	// 取消订单
	public String cancel() {
		String oid = request.getParameter("oid");
		// 校验订单状态
		
		int status = orderService.findOrderStatus(oid);
		if (status != 1) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能取消！");
			return "msg";
		}
		orderService.updateOrderStatus(oid, 5);// 设置状态为取消！
		request.setAttribute("code", "success");
		request.setAttribute("msg", "您的订单已取消！");
		return "msg";
	}

	// 发货功能
	public String deliver() {
		String oid = request.getParameter("oid");
		// 校验订单状态
		 
		int status = orderService.findOrderStatus(oid);
		if (status != 2) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能发货！");
			return "msg";
		}
		orderService.updateOrderStatus(oid, 3);// 设置状态为取消！
		request.setAttribute("code", "success");
		request.setAttribute("msg", "您的订单已发货，请查看物流！");
		return "msg";
	}
}
