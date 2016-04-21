package com.xjj.foodDeliveryServer.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Table(name="orders")
@Entity
public class Order implements Serializable{
	

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private User user;
	private Seller seller;
	@Transient
	private Map<Dish, Integer> dishAmountMap;
	@Column(length=2047)
	private String dishAmountMapJson;
	private Date time;
	private int status;
	private Double cost;
	@Transient
	private ObjectMapper objectMapper;
	
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDishAmountMapJson() {
		return dishAmountMapJson;
	}
	
	public void setDishAmountMapJson() {
		try {
			this.dishAmountMapJson = objectMapper.writeValueAsString(dishAmountMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calculateCost(){
		for(Dish dish : dishAmountMap.keySet()){
			cost += dish.getCost() * dishAmountMap.get(dish);
		}
	}

	public Double getCost() {
		return cost;
	}
	public Order() {
		// TODO Auto-generated constructor stub
		dishAmountMap = new HashMap<>();
		objectMapper = new ObjectMapper();
		cost = 0.0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@SuppressWarnings("unchecked")
	public Map<Dish, Integer> getDishAmountMap() {
		try {
			Map<String, Integer> map = objectMapper.readValue(dishAmountMapJson, Map.class);
			Map<Dish, Integer> mapToReturn = new HashMap<>();
			for (String s : map.keySet()){
				Dish dish = objectMapper.readValue(s, Dish.class);
				mapToReturn.put(dish, map.get(s));
			}
			return mapToReturn;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Order.java line 112...");
		return null;
	}

	public void setDishAmountMap(Map<Dish, Integer> dishAmountMap) {
		this.dishAmountMap = dishAmountMap;
	}
}
