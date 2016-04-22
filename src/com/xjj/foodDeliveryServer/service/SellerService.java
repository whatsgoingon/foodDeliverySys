package com.xjj.foodDeliveryServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjj.foodDeliveryServer.bean.Dish;
import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.Seller;
import com.xjj.foodDeliveryServer.dao.SellerDao;
import com.xjj.foodDeliveryServer.variables.QueueToExamine;

@Service("sellerService")
public class SellerService {
	@Autowired(required=true)
	private SellerDao sellerDao;

	public Seller getSeller(String tel, String password) {
		Seller seller = sellerDao.findSeller(tel);
		if(password.equals(seller.getPassword())){
			return seller;
		}else{
			System.out.println("wrong password");
			return null;
		}
	}
	
	public Seller getSellerById(Integer id){
		return sellerDao.findSellerById(id);
	}

	public List<Order> getAllOrders(Seller seller) {
		return sellerDao.getAllOrders(seller);
	}

	@Transactional
	public void addDish(Seller seller, Dish dish) {
		List<Dish> list = seller.getDishesList();
		list.add(dish);
		seller.setDishesList(list);
		sellerDao.updateSeller(seller);
	}

	@Transactional
	public void register(Seller seller) {
		sellerDao.addSeller(seller);
		QueueToExamine.getQueue().add(seller);
	}

	public List<Seller> getAllSellers() {
		return sellerDao.getAll();
	}

}