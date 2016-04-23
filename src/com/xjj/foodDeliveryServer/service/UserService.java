package com.xjj.foodDeliveryServer.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjj.foodDeliveryServer.bean.Dish;
import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.SellerUserPair;
import com.xjj.foodDeliveryServer.bean.User;
import com.xjj.foodDeliveryServer.dao.OrderDao;
import com.xjj.foodDeliveryServer.dao.UserDao;
import com.xjj.foodDeliveryServer.variables.QueueToExamine;
import com.xjj.foodDeliveryServer.variables.ShoppingCart;

@Service("userService")
public class UserService {
	@Autowired(required=true)
	private UserDao userDao;
	@Autowired(required=true)
	private OrderDao orderDao;

	public User getUser(String tel, String password){
		User user = userDao.findUser(tel);
		if(user==null){
			System.out.println("no such user, tel: " + tel);
			return null;
		}
		if (password.equals(user.getPassword())){
			return user;
		}else{
			System.out.println("wrong password");
			System.out.println("password is :" + user.getPassword());
			return null;
		}
	}
	
	public boolean isUserExisted(String tel){
		User user = userDao.findUser(tel);
		if (user==null) {
			return false;
		}else return true;
	}

	@Transactional
	public void register(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	public List<Order> getAllOrders(User user) {
		// TODO Auto-generated method stub
		return userDao.getAllOrders(user);
	}

	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	public void addDishToCart(Dish dish, User user) {
		// TODO Auto-generated method stub
		SellerUserPair pair = new SellerUserPair(dish.getSellerId(), user.getId());
		Order order;
		if (!ShoppingCart.getShoppingCartMap().containsKey(pair)){
			System.out.println("not contains pair...");
			order = new Order();
			order.setSellerId(dish.getSellerId());
			order.setUserId(user.getId());
		}else{
			order = ShoppingCart.getShoppingCartMap().get(pair);
		}
		Map<Dish, Integer> map = order.getDishAmountMap();
		if(map.containsKey(dish)){
			map.put(dish, map.get(dish)+1);
		}else{
			map.put(dish, 1);
		}
		order.setDishAmountMap(map);
		order.calculateCost();
		Map<SellerUserPair, Order> map2 = ShoppingCart.getShoppingCartMap();
		map2.put(pair, order);
		ShoppingCart.setShoppingCartMap(map2);
		System.out.println("added to cart...");
		System.out.println(order.getCost());
		System.out.println(order.getDishAmountMapJson());
		
	}
	
	@Transactional
	public void checkout(Integer userId, Integer sellerId){
		SellerUserPair pair = new SellerUserPair(sellerId, userId);
		Order order = ShoppingCart.getShoppingCartMap().get(pair);
		order.setStatus(1);						//order status set to 1, means to be paid
		order.setTime(new Date());
		orderDao.addOrder(order);
		Map<SellerUserPair, Order> map = ShoppingCart.getShoppingCartMap();
		map.remove(pair);
		ShoppingCart.setShoppingCartMap(map);	//clear shopping cart
		
	}

	@Transactional
	public void payForOrder(String uuid) {
		// TODO Auto-generated method stub
		Order order = orderDao.findOrderById(uuid);
		order.setStatus(2);
		orderDao.updateOrder(order);
		
		QueueToExamine.orderQueue().add(order);
		
	}
	
	@Test
	public void test(){
		System.out.println("#############");
		System.out.println(QueueToExamine.orderQueue().size());
	}
	
}
