package com.xjj.foodDeliveryServer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public boolean login(String tel, String password, HttpServletRequest request){
		Seller seller = sellerService.getSeller(tel, password);
		if(seller == null){
			return false;
		}
		request.getSession().setAttribute("seller", seller);
		return true;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public void register(Seller seller){
		sellerService.register(seller);
	}
	
	@RequestMapping(value="/orders", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Order> getAllOrdersBySeller(HttpServletRequest request){
		Seller seller = (Seller)request.getSession().getAttribute("seller");
		System.out.println(seller.getName());
		return sellerService.getAllOrders(seller);
	}
	
	@RequestMapping(value="/addDish", method=RequestMethod.POST)
	@ResponseBody
	public boolean addDish (Dish dish, HttpServletRequest request){
		Seller seller = (Seller) request.getSession().getAttribute("seller");
		sellerService.addDish(seller, dish);
		return true;
	}
	
	@RequestMapping(value="/dishes", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Dish> getAllDishesBySeller(HttpServletRequest request){
		Seller seller = (Seller) request.getSession().getAttribute("seller");
		return seller.getDishesList();
	}
	
	@RequestMapping(value="/all", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Seller> getAllSellers(){
		return sellerService.getAllSellers();
	}
}
