package com.xjj.foodDeliveryServer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjj.foodDeliveryServer.bean.Dish;
import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.SellerUserPair;
import com.xjj.foodDeliveryServer.bean.User;
import com.xjj.foodDeliveryServer.service.SellerService;
import com.xjj.foodDeliveryServer.service.UserService;
import com.xjj.foodDeliveryServer.variables.QueueToExamine;
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
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public User login(String tel, String password, HttpServletRequest request){
//		System.out.println("tel: " + tel);
		User user = userService.getUser(tel, password);
		if(user==null)return null;
		request.getSession().setAttribute("user", user);
		return user;
	}
	
	
	/**
	 *	uri:		/user/register
	 *	method:		post
	 *	dataForm:	tel={user.tel}&password={user.password(md5digest)}...
	 *
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public boolean register(User user){
		System.out.println("here");
		userService.register(user);
		return true;
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
	
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(User user, HttpServletRequest request){
//		User u = (User)request.getSession().getAttribute("user");
		userService.update(user);
		return true;
	}
	
	@RequestMapping(value="/orders", produces="application/json; charset=utf-8")
	@ResponseBody
	public List<Order> getAllOrderesByUser(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		return userService.getAllOrders(user);
	}
	
	@RequestMapping(value="/cart/{sellerId}", produces="application/json; charset=utf-8")
	@ResponseBody
	public Order getShoppingCart(@PathVariable("sellerId") Integer sellerId, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		SellerUserPair pair = new SellerUserPair(sellerId, user.getId());
		return ShoppingCart.getShoppingCartMap().get(pair);
	}
	
	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
	@ResponseBody
	public boolean addToCart(Dish dish, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		userService.addDishToCart(dish,user);
		return true;
	}
	
	@RequestMapping(value="/cart/{sellerId}/checkout")
	@ResponseBody
	public boolean checkout(HttpServletRequest request, @PathVariable("sellerId") Integer sellerId){
		User user = (User)request.getSession().getAttribute("user");
		userService.checkout(user.getId(), sellerId);
		return true;
	}
	
	@RequestMapping(value="/orders/{orderId}/pay")
	@ResponseBody
	public boolean payForOrder(@PathVariable("orderId") String uuid){
		userService.payForOrder(uuid);
		return true;
	}
	@Test
	public void Test(){
		System.out.println(QueueToExamine.orderQueue().size());
	}
	
}
