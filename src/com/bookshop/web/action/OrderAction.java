package com.bookshop.web.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.domain.Book;
import com.bookshop.domain.CartItem;
import com.bookshop.domain.Order;
import com.bookshop.domain.OrderItem;
import com.bookshop.domain.User;
import com.bookshop.pager.PageBean;
import com.bookshop.service.CartItemService;
import com.bookshop.service.OrderService;
import com.bookshop.utils.BeansHelper;
import com.bookshop.utils.PaymentUtil;
import com.bookshop.web.form.UserForm;

public class OrderAction extends BaseAction {
	OrderService orderService = (OrderService) BeansHelper.getBeanService("orderService");//new OrderService();
	CartItemService cartService = (CartItemService) BeansHelper.getBeanService("cartItemService");//new CartItemService();

	// 1.我的订单
	public String myOrders() {
		// 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		int pc = getPc();
		// 2. 得到url：...
		String url = getUrl();
		// 3. 从当前session中获取User
		UserForm user = (UserForm) request.getSession().getAttribute(
				"sessionUser");

		// 4. 使用pc和cid调用service#myOrders得到PageBean
		PageBean<Order> pb = orderService.myOrders(user.getUid(), pc);
		// 5. 给PageBean设置url，保存PageBean，转发到/jsps/order/list.jsp
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "orderlist";
	}

	// 我的订单
	public String createOrder() {
		// 1.初始化参数orderItemIds,通过它调用CartItemService的方法获取购物车的商品Item
		String orderItemIds = request.getParameter("orderItemIds");
		List<CartItem> cartItems = cartService.loadCartItems(orderItemIds);
		// 2.创建Order
		Order order = new Order();
		order.setOrdertime(String.format("%tF %<tT", new Date()));
		order.setStatus(1);
		order.setAddress(request.getParameter("address"));// 设置收货地址
		UserForm user = (UserForm) request.getSession().getAttribute(
				"sessionUser");
		order.setOwner(convertVoToPo(user));
		BigDecimal total = new BigDecimal("0");
		for (CartItem cartItem : cartItems) {
			total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
		}
		order.setTotal(total.doubleValue());// 设置总计
		// 3生成List转成Set<OrderItem>
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		for (CartItem item : cartItems) {
			OrderItem orderItem = new OrderItem();
			Book book = item.getBook();
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSubtotal(item.getSubtotal());
			orderItem.setBook(book);
			orderItem.setBname(book.getBname());
			orderItem.setImage_b(book.getImage_b());
			orderItem.setCurrPrice(book.getCurrPrice());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}
		order.setOrderItemList(orderItems);
		// 4.请求创建方法
		orderService.creatOrder(order);
		// 创建成订单的条目就要从购物车中删除， 删除购物车条目!!!!!!!!!!
		cartService.delete(orderItemIds);
		// 5. 保存订单，转发到ordersucc.jsp
		request.setAttribute("order", order);
		return "ordersucc";
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
		return "loadOrder";
	}

	// 用户取消订单时
	public String cancel() {
		// 1.获取要订单好oid,并查询其状态
		String oid = request.getParameter("oid");
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

	// 用户确认订单时
	public String confirm() {
		// 1.获取要订单好oid,并查询其状态
		String oid = request.getParameter("oid");
		int status = orderService.findOrderStatus(oid);
		if (status != 3) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能确认收货！");
			return "msg";
		}
		orderService.updateOrderStatus(oid, 4);// 设置状态为交易成功！
		request.setAttribute("code", "success");
		request.setAttribute("msg", "恭喜，交易成功！");
		return "msg";
	}

	// 支付准备
	public String paymentPre() {
		// 获取订单信息，转发到pay.jsp
		request.setAttribute("order",
				orderService.loadOrderByOid(request.getParameter("oid")));
		return "paymentPre";
	}

	// 支付！！！！！！！！！！！！！支付方法

	public String payment() throws IOException {
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("payment.properties"));
		/*
		 * 1. 准备13个参数
		 */
		String p0_Cmd = "Buy";// 业务类型，固定值Buy
		String p1_MerId = props.getProperty("p1_MerId");// 商号编码，在易宝的唯一标识
		String p2_Order = request.getParameter("oid");// 订单编码
		String p3_Amt = "0.01";// 支付金额
		String p4_Cur = "CNY";// 交易币种，固定值CNY
		String p5_Pid = "";// 商品名称
		String p6_Pcat = "";// 商品种类
		String p7_Pdesc = "";// 商品描述
		String p8_Url = props.getProperty("p8_Url");// 在支付成功后，易宝会访问这个地址。
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 扩展信息
		String pd_FrpId = request.getParameter("yh");// 支付通道
		String pr_NeedResponse = "1";// 应答机制，固定值1

		/*
		 * 2. 计算hmac 需要13个参数 需要keyValue 需要加密算法
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);

		/*
		 * 3. 重定向到易宝的支付网关
		 */
		StringBuilder sb = new StringBuilder(
				"https://www.yeepay.com/app-merchant-proxy/node");
		sb.append("?").append("p0_Cmd=").append(p0_Cmd);
		sb.append("&").append("p1_MerId=").append(p1_MerId);
		sb.append("&").append("p2_Order=").append(p2_Order);
		sb.append("&").append("p3_Amt=").append(p3_Amt);
		sb.append("&").append("p4_Cur=").append(p4_Cur);
		sb.append("&").append("p5_Pid=").append(p5_Pid);
		sb.append("&").append("p6_Pcat=").append(p6_Pcat);
		sb.append("&").append("p7_Pdesc=").append(p7_Pdesc);
		sb.append("&").append("p8_Url=").append(p8_Url);
		sb.append("&").append("p9_SAF=").append(p9_SAF);
		sb.append("&").append("pa_MP=").append(pa_MP);
		sb.append("&").append("pd_FrpId=").append(pd_FrpId);
		sb.append("&").append("pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&").append("hmac=").append(hmac);

		response.sendRedirect(sb.toString());
		return null;
	}

	/**
	 * 回馈方法 当支付成功时，易宝会访问这里 用两种方法访问： 1. 引导用户的浏览器重定向(如果用户关闭了浏览器，就不能访问这里了) 2.
	 * 易宝的服务器会使用点对点通讯的方法访问这个方法。（必须回馈success，不然易宝服务器会一直调用这个方法）
	 */
	public String back(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取12个参数
		 */
		String p1_MerId = req.getParameter("p1_MerId");
		String r0_Cmd = req.getParameter("r0_Cmd");
		String r1_Code = req.getParameter("r1_Code");
		String r2_TrxId = req.getParameter("r2_TrxId");
		String r3_Amt = req.getParameter("r3_Amt");
		String r4_Cur = req.getParameter("r4_Cur");
		String r5_Pid = req.getParameter("r5_Pid");
		String r6_Order = req.getParameter("r6_Order");
		String r7_Uid = req.getParameter("r7_Uid");
		String r8_MP = req.getParameter("r8_MP");
		String r9_BType = req.getParameter("r9_BType");
		String hmac = req.getParameter("hmac");
		/*
		 * 2. 获取keyValue
		 */
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("payment.properties"));
		String keyValue = props.getProperty("keyValue");
		/*
		 * 3. 调用PaymentUtil的校验方法来校验调用者的身份 >如果校验失败：保存错误信息，转发到msg.jsp >如果校验通过： *
		 * 判断访问的方法是重定向还是点对点，如果要是重定向 修改订单状态，保存成功信息，转发到msg.jsp *
		 * 如果是点对点：修改订单状态，返回success
		 */
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (!bool) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "无效的签名，支付失败！");
			return "msg";
		}
		if (r1_Code.equals("1")) {
			orderService.updateOrderStatus(r6_Order, 2);
			if (r9_BType.equals("1")) {
				req.setAttribute("code", "success");
				req.setAttribute("msg", "恭喜，支付成功！");
				return "msg";
			} else if (r9_BType.equals("2")) {
				resp.getWriter().print("success");
			}
		}
		return null;
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

	private User convertVoToPo(UserForm userForm) {
		User user = new User();
		user.setLoginname(userForm.getLoginname());
		user.setUid(userForm.getUid());
		user.setStatus(userForm.isStatus());
		return user;
	}

}
