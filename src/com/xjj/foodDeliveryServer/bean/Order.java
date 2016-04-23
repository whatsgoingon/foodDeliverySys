package com.xjj.foodDeliveryServer.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

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
	private String uuid;
	private Integer userId;
	private Integer sellerId;
	@Transient
	private Map<Dish, Integer> dishAmountMap;
	@Column(length=2047)
	private String dishAmountMapJson;
	private Date time;
	private int status;
	private double cost;
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
			Map<String , Integer> map = new HashMap<>();
			for(Entry<Dish, Integer> entries: dishAmountMap.entrySet()){
				map.put(objectMapper.writeValueAsString(entries.getKey()), entries.getValue());
			}
			this.dishAmountMapJson = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calculateCost(){
		double sum = 0;
		for(Dish dish : dishAmountMap.keySet()){
			sum += dish.getCost() * dishAmountMap.get(dish);
		}
		cost = sum;
	}

	public Double getCost() {
		return cost;
	}
	public Order() {
		// TODO Auto-generated constructor stub
		dishAmountMap = new HashMap<>();
		objectMapper = new ObjectMapper();
		cost = 0.0;
		status = 0;
		uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@SuppressWarnings("unchecked")
	public Map<Dish, Integer> getDishAmountMap() {
		if(dishAmountMapJson==null){
			return new HashMap<>();
		}
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
		setDishAmountMapJson();
	}
}
