package com.xjj.foodDeliveryServer.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xjj.foodDeliveryServer.bean.Order;

@Repository("orderDao")
public class OrderDao {
	@PersistenceContext(name = "un")
	private EntityManager em;
	
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		em.merge(order);
	}

	public Order findOrderById(String uuid) {
		// TODO Auto-generated method stub
		return em.find(Order.class, uuid);
	}

	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		em.merge(order);
	}
}
