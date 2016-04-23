package com.xjj.foodDeliveryServer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.Seller;

@Repository("sellerDao")
public class SellerDao {
	@PersistenceContext(name = "un")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Seller findSeller(String tel) {
		String jpql = "select seller from Seller seller where seller.tel=:tel";
		List<Seller> ls = em.createQuery(jpql)
				.setParameter("tel", tel).getResultList();
		if (ls.isEmpty())
			return null;
		else
			return ls.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders(Seller seller) {
		String jpql = "select order from Order order where order.seller=:seller";
		List<Order> list = em.createQuery(jpql).setParameter("seller", seller).getResultList();
		System.out.println("###" + list.size());
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	public Seller findSellerById(Integer id) {
		return em.find(Seller.class, id);
	}

	public void updateSeller(Seller seller) {
		// TODO Auto-generated method stub
		em.merge(seller);
//		String jpql = "UPDATE Seller seller SET seller.dishesListJson=:listJson where seller.id=:id";
//		em.createQuery(jpql).setParameter("id", seller.getId()).setParameter("listJson", seller.getDishesListJson()).executeUpdate();
		
	}

	public void addSeller(Seller seller) {
		// TODO Auto-generated method stub
		em.persist(seller);
	}

	@SuppressWarnings("unchecked")
	public List<Seller> getAll() {
		// TODO Auto-generated method stub
		String jpql = "select seller from Seller seller";
		List<Seller> list = em.createQuery(jpql).getResultList();
		if (list.isEmpty())
			return null;
		else
			return list;
	}


}
