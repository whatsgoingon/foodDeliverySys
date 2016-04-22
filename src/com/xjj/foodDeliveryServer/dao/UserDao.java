package com.xjj.foodDeliveryServer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.User;

@Repository("userDao")
public class UserDao {
	@PersistenceContext(name = "un")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public User findUser(String tel) {
		System.out.println("tel: " + tel);
		String jpql = "select user from User user where user.tel=:tel";
		List<User> ls = em.createQuery(jpql).setParameter("tel", tel).getResultList();
		if (ls.isEmpty())
			return null;
		else
			return ls.get(0);
	}

	public void addUser(User user) {
		em.persist(user);
	}

	public void updateUser(User user) {
		em.merge(user);
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders(User user) {
		// TODO Auto-generated method stub
		String jpql = "select order from Order order where order.user=:user";
		List<Order> list = em.createQuery(jpql).setParameter("user", user).getResultList();
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return em.find(User.class, userId);
	}

}
