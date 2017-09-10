package com.bookshop.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bookshop.domain.Book;
import com.bookshop.domain.CartItem;
import com.bookshop.domain.Category;
import com.bookshop.domain.Order;
import com.bookshop.domain.User;
import com.bookshop.utils.DBHelper;

public class TestHibernate {
	public static void main(String args[]) {
		// queryUser();
		// queryCategory();
		// queryBook();
		// queryCartItem();
		// queryOrderByOid();
		//queryOrderByUid();
		//addCartItem();
		String cid = "1111";
		getChilds(cid);
	}
	public static void getChilds(String cid){
		Session session = DBHelper.getSession();
		Transaction tx = session.beginTransaction();
		// 通过pid=cid查询子分类数
		//Hibernate中不能用count(1)来查询记录数要用count(*)
		//Exception in thread "main" org.hibernate.hql.ast.QuerySyntaxException: unexpected token: 1 near line 1, column 14 [select count(1) from com.bookshop.domain.Category where pid = ?]
		String hql = "select count(*) from Category where pid = ?";
		Query query = session.createQuery(hql);
		//query.setParameter(0,1)会报错，因为数据库中pid是char类型
		query.setParameter(0, cid);
		Number number = (Number) query.uniqueResult();
		tx.commit();
		DBHelper.closeSession();
		System.out.println(number);
	}
	public static void addUser() {
		Session session = DBHelper.getSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setLoginname("woyaobianqiang");
		user.setLoginpass("nowisnow");
		user.setEmail("www.shunzia.com");
		user.setStatus(true);
		session.save(user);
		tx.commit();
		DBHelper.closeSession();

	}

	public static List<User> queryUser() {
		String hql = "from User";
		Session session = DBHelper.getSession();
		Query query = session.createQuery(hql);
		List<User> users = query.list();
		System.out.println(users);

		return null;

	}

	// 测试Category的映射
	public static List<Category> queryCategory() {
		String hql = "from Category where cid = '1'";
		Session session = DBHelper.getSession();
		Query query = session.createQuery(hql);
		Category category = (Category) query.uniqueResult();
		System.out.println("是否有父分类:" + category.getParent());
		System.out.println("子分类个数:" + category.getChildren().size());
		System.out.println(category);
		// 循环将二级分类打印出来
		System.out.println("<---------二级分类---------->");
		int i = 1;
		for (Category item : category.getChildren()) {
			System.out.println((i++) + "--:" + item);
		}
		DBHelper.closeSession();
		return null;

	}

	// 测试Category的映射
	public static void queryBook() {
		String hql = "from Book where bid = '0EE8A0AE69154287A378FB110FF2C780'";
		Session session = DBHelper.getSession();
		Query query = session.createQuery(hql);
		Book book = (Book) query.uniqueResult();
		// 循环将二级分类打印出来
		System.out.println("<---------一本书的信息---------->");
		System.out.println(book);
		DBHelper.closeSession();

	}

	// 测试CartItem的映射
	public static void queryCartItem() {
		String hql = "from CartItem where uid = '55790D9C1A1845738E6D93866A148C7E'";
		Session session = DBHelper.getSession();
		Query query = session.createQuery(hql);
		List<CartItem> cartItems = query.list();
		// 循环将二级分类打印出来
		System.out.println("<---------购物车的物品---------->");
		int i = 1;
		System.out.println(cartItems.size());
		System.out.println("购物车的拥有者:" + cartItems.get(0).getUser());
		for (CartItem item : cartItems) {
			System.out.println("物品项" + i + ":" + item);
			System.out.println("物品项" + i + "的书本:" + item.getBook());
			i++;
		}

		DBHelper.closeSession();

	}

	public static void addCartItem() {
		Session session = DBHelper.getSession();
		Transaction tx = session.beginTransaction();
		CartItem cartItem = new CartItem();
		Book book = new Book();
		User user = new User();
		book.setBid("0BE0707984014E66BD9F34F534FCE0F7");
		user.setUid("55790D9C1A1845738E6D93866A148C7E");
		cartItem.setBook(book);
		cartItem.setUser(user);
		session.save(cartItem);
		tx.commit();
		DBHelper.closeSession();

	}

	// 测试Order的映射
	public static void queryOrder() {
		String hql = "from Order where oid = '0A1F4C5BDE754277B984D1E7E763051D' ";
		// String hql =
		// "from Order o,User u where u.uid = '55790D9C1A1845738E6D93866A148C7E' and u.uid = o.uid limit 0,5 ";
		Session session = DBHelper.getSession();
		Query query = session.createQuery(hql);
		Order order = (Order) query.uniqueResult();
		System.out.println(order.getOrdertime());
		System.out.println("订单的拥有者:" + order.getOwner());
		System.out.println("订单:" + order);
		// List<Order> orders = query.list();
		// // 循环将二级分类打印出来
		// System.out.println("<---------该用户的订单信息---------->");
		// int i = 1;
		// System.out.println(orders.size());
		// System.out.println("订单的拥有者:" + orders.get(0).getOwner());
		// for (Order item : orders) {
		// System.out.println("订单" + i + ":" + item);
		// System.out.println("订单" + i + "的总物品数:"
		// + item.getOrderItemList().size());
		// i++;
		// }
		DBHelper.closeSession();

	}// 测试Order的映射

	public static void queryOrderByUid() {
		String hql = "from Order  where uid = '55790D9C1A1845738E6D93866A148C7E' ";
		Session session = DBHelper.getSession();
		Query query = session.createQuery(hql);
		List<Order> orders = query.list();
		// 循环将二级分类打印出来
		System.out.println("<---------该用户的订单信息---------->");
		int i = 1;
		System.out.println(orders.size());
		System.out.println("订单的拥有者:" + orders.get(0).getOwner());
		for (Order item : orders) {
			System.out.println("订单" + i + ":" + item);
			System.out.println("订单" + i + "的总物品数:" + item.getOrderItemList());
			i++;
		}
		DBHelper.closeSession();
	}
}
