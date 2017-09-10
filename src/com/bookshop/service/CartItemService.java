package com.bookshop.service;

import java.util.List;

import com.bookshop.dao.CartItemDao;
import com.bookshop.domain.CartItem;

public class CartItemService {
	private CartItemDao cartItemDao;// = new CartItemDao();

	public void setCartItemDao(CartItemDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}

	// 加载已提交的订单
	public List<CartItem> loadCartItems(String cartItemIds) {
		return cartItemDao.loadCartItems(cartItemIds);
	}

	// jia或jian对数量的异步更新
	public CartItem updateCartItemQuantity(String cartItemId, int quantity) {
		// 1.先判断quantity的值
		if (quantity == 0) {// 说明减掉这一条后就没有了即是删除了

			cartItemDao.batchDelete(cartItemId);

		} else {// 否则更新数据
			cartItemDao.upadteQuantity(cartItemId, quantity);
		}
		// 要在查询一遍,将更新后的数据返回
		CartItem cartItem = cartItemDao.findByCartItemId(cartItemId);
		return cartItem;
	}

	// 删除商品
	public void delete(String cartItemIds) {
		cartItemDao.batchDelete(cartItemIds);
	}

	// 我的购物车,得到购物车中的数据
	public List<CartItem> myCart(String uid) {
		return cartItemDao.myCart(uid);
	}

	// 添加购买的书的记录
	public void add(CartItem cartItem) {

		String bid = cartItem.getBook().getBid();
		String uid = cartItem.getUser().getUid();
		// 1首先查询该书是否已经有记录了
		CartItem item = cartItemDao.findBookByBidAndUid(bid, uid);
		System.out.println("item:" + item);
		if (item == null) {// 空则将前台的表单数据存入数据库
			// 要补全数据，填上主键
			// cartItem.setCartItemId(CommonUtils.uuid());
			cartItemDao.add(cartItem, bid, uid);
		} else {
			// 否则更新数据即可
			int quantity = cartItem.getQuantity() + item.getQuantity();
			cartItemDao.upadteQuantity(item.getCartItemId(), quantity);
		}
	}

}
