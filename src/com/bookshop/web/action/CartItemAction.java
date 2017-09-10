package com.bookshop.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.bookshop.domain.Book;
import com.bookshop.domain.CartItem;
import com.bookshop.domain.User;
import com.bookshop.service.CartItemService;
import com.bookshop.utils.BeansHelper;
import com.bookshop.web.form.UserForm;
import com.opensymphony.xwork2.ModelDriven;

public class CartItemAction extends BaseAction implements ModelDriven<CartItem> {
	private CartItem cartItem;
	private CartItemService cartItemService = (CartItemService) BeansHelper.getBeanService("cartItemService");//new CartItemService();

	@Override
	public CartItem getModel() {
		if (cartItem == null)
			cartItem = new CartItem();
		return cartItem;
	}

	// 1.我的购物车
	public String myCart() {
		// 1.通过seesion获取当前用户的uid
		UserForm user = (UserForm) request.getSession().getAttribute(
				"sessionUser");
		String uid = user.getUid();
		// 2、根据当前用户的uid得到其购物车中的数据
		List<CartItem> listItem = cartItemService.myCart(uid);
		request.setAttribute("cartItemList", listItem);
		System.out.println("cart size:" + listItem.size());
		System.out.println("查看购物车");
		return "myCart";
	}

	// 添加书本到购物车，跳转到购物车页面
	public String add() {
		// 1.通过seesion获取当前用户user
		UserForm user = (UserForm) request.getSession().getAttribute(
				"sessionUser");
		String bid = request.getParameter("bid");
		Book book = new Book();
		book.setBid(bid);
		cartItem.setBook(book);
		cartItem.setUser(convertVoToPo(user));
		// 3调用service的add方法执行
		cartItemService.add(cartItem);
		// 4.跳转到我的购物车页面
		return myCart();
	}

	// 删除和批量删除
	public String batchDelete() {
		// 获取参数，该参数以逗号相连
		String cartItemIds = (String) request.getParameter("cartItemIds");
		cartItemService.delete(cartItemIds);
		// 跳转到我的购物车页面
		return myCart();
	}

	// jia或jian更新数量，异步请求来的
	public void updateQuantity() throws IOException {
		// 1.获取参数cartItemId,quantity
		String cartItemId = (String) request.getParameter("cartItemId");
		String quantity = (String) request.getParameter("quantity");
		CartItem cartItem = cartItemService.updateCartItemQuantity(cartItemId,
				Integer.parseInt(quantity));
		// 2返回json数据给前台，json对象
		StringBuilder result = new StringBuilder("{");
		result.append("\"quantity\"").append(":")
				.append(cartItem.getQuantity()).append(",");
		result.append("\"subtotal\"").append(":")
				.append(cartItem.getSubtotal());
		result.append("}");
		// 3.写出
		response.getWriter().print(result);
	}

	// 点击结算后，跳到showitem.jsp显示订单信息，供用户操作
	public String loadCartItems() {
		// 1获取参数，该参数以逗号相连
		String cartItemIds = (String) request.getParameter("cartItemIds");
		// 2调用service获取List<CartItem>
		List<CartItem> listCartItems = cartItemService
				.loadCartItems(cartItemIds);
		request.setAttribute("cartItemList", listCartItems);
		request.setAttribute("cartItemIds", cartItemIds);
		// 转发到showItem.jsp
		return "load";
	}

	private User convertVoToPo(UserForm userForm) {
		User user = new User();
		user.setLoginname(userForm.getLoginname());
		user.setUid(userForm.getUid());
		user.setStatus(userForm.isStatus());
		return user;
	}

}
