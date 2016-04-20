package com.xjj.foodDeliveryServer.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "seller")
public class Seller implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private double rate;
	private String tel;
	@Column(length=1023)
	private String dishesListJson;
	@Transient
	private List<Dish> dishesList;
	@Transient
	private ObjectMapper objectMapper;
	
	public void setDishesListJson() {
		
		try {
			this.dishesListJson = objectMapper.writeValueAsString(dishesList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDishesListJson() {
		return dishesListJson;
	}
	
	public Seller() {
		// TODO Auto-generated constructor stub
		dishesList = new ArrayList<>();
		objectMapper = new ObjectMapper();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@SuppressWarnings("unchecked")
	public List<Dish> getDishesList() {
		
		try {
			return objectMapper.readValue(dishesListJson, List.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("null list");
		return null;
	}

	public void setDishesList(List<Dish> dishesList) {
		this.dishesList = dishesList;
	}
	
	
	
	
}
