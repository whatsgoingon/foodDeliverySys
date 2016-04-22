package com.xjj.foodDeliveryServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.Seller;
import com.xjj.foodDeliveryServer.bean.SellerUserPair;
import com.xjj.foodDeliveryServer.bean.User;
import com.xjj.foodDeliveryServer.service.SellerService;
import com.xjj.foodDeliveryServer.service.UserService;
import com.xjj.foodDeliveryServer.variables.ShoppingCart;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired(required=true)
	private UserService userService;
	@Autowired(required=true)
	private SellerService sellerService;
	
	/**
	 * 
	 *	uri:		/user/login
	 *	method:		post
	 *	dataForm:	tel={tel}&password={password}
	 *
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public User login(String tel, String password){
		return userService.getUser(tel, password);
	}
	
	
	/**
	 *	uri:		/user/register
	 *	method:		post
	 *	dataForm:	tel={user.tel}&password={user.password(md5digest)}...
	 *
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public User register(User user){
		userService.register(user);
		return user;
	}
	
	/**
	 * 
	 * uri:		/user/telVerify/{tel}
	 * result:	true if existed
	 * 			false if not existed
	 * 
	 */
	@RequestMapping(value="/telVerify/{tel}")
	@ResponseBody
	public boolean telVerify(@PathVariable("tel") String tel){
		return userService.isUserExisted(tel);
	}
	
	
	@RequestMapping(value="/update", method = RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public User update(User user){
		userService.update(user);
		return null;
	}
	
	@RequestMapping(value="/orders", method = RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Order> getAllOrderesByUser(User user){
		return userService.getAllOrders(user);
	}
	
	@RequestMapping(value="/cart/{userId}/{sellerId}", produces="application/json; charset=utf-8")
	@ResponseBody
	public Order getShoppingCart(@PathVariable("userId") Integer userId, @PathVariable("sellerId") Integer sellerId){
		User user = userService.getUserById(userId);
		Seller seller = sellerService.getSellerById(sellerId);
		SellerUserPair pair = new SellerUserPair(seller, user);
		return ShoppingCart.getOrderByPair(pair);
	}
}
