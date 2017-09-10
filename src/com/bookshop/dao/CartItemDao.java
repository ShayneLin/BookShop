package com.bookshop.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bookshop.domain.CartItem;
import com.bookshop.utils.DBHelper;

public class CartItemDao extends HibernateDaoSupport {

	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 查询被勾选的订单
	public List<CartItem> loadCartItems(String cartItemIds) {
		// 1切分cartItemIds
		final Object[] params = cartItemIds.split(",");
		List<CartItem> cartListItems = this.getHibernateTemplate().execute(
				new HibernateCallback<List<CartItem>>() {

					@Override
					public List<CartItem> doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "select c from CartItem c, Book b where b.bid = c.bid "
								+ "and cartItemId in (:itemIds)";
						Query query = session.createQuery(hql);
						query.setParameterList("itemIds", params);
						List<CartItem> cardListItems = query.list();
						return cardListItems;
					}
				});
		return cartListItems;
	}

	// 按照cartItemId查询记录
	public CartItem findByCartItemId(String cartItemId) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String sql = " from  CartItem c, Book b where c.bid=b.bid and c.cartItemId= ?";
		// Query query = session.createQuery(sql);
		// query.setParameter(0, cartItemId);
		// CartItem cartItem = (CartItem) query.uniqueResult();
		System.out.println("size="
				+ this.getHibernateTemplate().find(sql, cartItemId).size());
		CartItem cartItem = null;
		List list = this.getHibernateTemplate().find(sql, cartItemId);
		if (list.size() > 0)
			cartItem = (CartItem) list.get(0);
		// tx.commit();
		// DBHelper.closeSession();
		return cartItem;
	}

	// 删除功能,包括批量删除
	public void batchDelete(String cartItemIds) {
		// // 1.切分字符串,生成whereSql子句
		final Object[] params = cartItemIds.split(",");
		// // 注意写法
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete from T_CartItem where 1=1 "
						+ " and cartItemId in (:itemIds)";
				Query query = session.createSQLQuery(sql);
				// hibernate会帮你组织in的内容
				query.setParameterList("itemIds", params);
				// 执行更新
				query.executeUpdate();
				return null;
			}

		});
		// tx.commit();
		// DBHelper.closeSession();

	}

	// 若是已经存在则更新数目
	public void upadteQuantity(String cartItemId, int quantity) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		// 先加载，再修改
		// CartItem cartItem = (CartItem) session.get(CartItem.class,
		// cartItemId);
		CartItem cartItem = (CartItem) this.getHibernateTemplate().get(
				CartItem.class, cartItemId);
		// 设置条目数
		cartItem.setQuantity(quantity);
		// session.update(cartItem);
		this.getHibernateTemplate().update(cartItem);
		// session.flush();
		// tx.commit();
		// DBHelper.closeSession();
	}

	// 若表中不曾有买该书，则增加一本书
	public void add(CartItem cartItem, String bid, String uid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		// session.save(cartItem);
		this.getHibernateTemplate().save(cartItem);
		// session.flush();
		// tx.commit();
		//DBHelper.closeSession();
	}

	// 查找添加的图书是否已经存在,并获取出来
	public CartItem findBookByBidAndUid(String bid, String uid) {
		// Session session = DBHelper.getSession();
		// Transaction tx = session.beginTransaction();
		String hql = "from CartItem where bid = ? and uid = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, bid);
		// query.setParameter(1, uid);
		// CartItem cartItem = (CartItem) query.uniqueResult();
		CartItem cartItem = null;
		List list = this.getHibernateTemplate().find(hql,
				new Object[] { bid, uid });
		if (list.size() > 0)
			cartItem = (CartItem) list.get(0);
		// tx.commit();
		// DBHelper.closeSession();
		return cartItem;
	}

	// 我的购物车
	public List<CartItem> myCart(String uid) {
		// 1.多表连接查询
		String hql = "select c from CartItem c,Book b where c.bid=b.bid and uid=? order by c.orderBy";
		// Session session = DBHelper.getSession();
		// 先清除之前的缓存
		// session.clear();
		// Transaction tx = session.beginTransaction();
		// Query query = session.createQuery(hql);
		// query.setParameter(0, uid);
		// List<CartItem> maplist = query.list();
		List<CartItem> maplist = this.getHibernateTemplate().find(hql, uid);
		System.out.println("物品数:" + maplist.size());
		// tx.commit();
		// DBHelper.closeSession();
		return maplist;
	}
}
