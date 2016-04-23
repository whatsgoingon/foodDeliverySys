package com.xjj.foodDeliveryServer.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xjj.foodDeliveryServer.bean.Dish;

@Repository("dishDao")
public class DishDao {
	@PersistenceContext(name = "un")
	private EntityManager em;
	
	public Dish findDishById(String uuid){
		return em.find(Dish.class, uuid);
	}
}
