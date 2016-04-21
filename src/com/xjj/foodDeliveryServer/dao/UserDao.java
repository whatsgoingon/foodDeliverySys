package com.xjj.foodDeliveryServer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

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

}
