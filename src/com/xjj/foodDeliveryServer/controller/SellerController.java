package com.xjj.foodDeliveryServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjj.foodDeliveryServer.bean.Dish;
import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.Seller;
import com.xjj.foodDeliveryServer.service.SellerService;

@Controller
@RequestMapping(value="/seller")
public class SellerController {
	@Autowired(required=true)
	private SellerService sellerService;

	@RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public Seller login(String tel, String password){
		return sellerService.getSeller(tel, password);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public void register(Seller seller){
		sellerService.register(seller);
	}
	
	@RequestMapping(value="/{sellerId}/orders", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Order> getAllOrdersBySeller(@PathVariable("sellerId") Integer id){
		Seller seller = new Seller();
		seller.setId(id);
		return sellerService.getAllOrders(seller);
	}
	
	@RequestMapping(value="/{sellerId}/addDish", method=RequestMethod.POST)
	@ResponseBody
	public void addDish(@PathVariable("sellerId") Integer id, Dish dish){
		Seller seller = sellerService.getSellerById(id);
		sellerService.addDish(seller, dish);
	}
	
	@RequestMapping(value="/{sellerId}/dishes", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Dish> getAllDishesBySeller(@PathVariable("sellerId") Integer id){
		Seller seller = sellerService.getSellerById(id);
		return seller.getDishesList();
	}
	
	@RequestMapping(value="/all", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Seller> getAllSellers(){
		return sellerService.getAllSellers();
	}
}
