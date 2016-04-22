package com.xjj.foodDeliveryServer.variables;

import java.util.HashMap;
import java.util.Map;

import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.SellerUserPair;

public class ShoppingCart {
	private static Map<SellerUserPair, Order> shoppingCart = null;

	public static Map<SellerUserPair, Order> getShoppingCart() {
		if (shoppingCart == null) {
			shoppingCart = new HashMap<>();
			return shoppingCart;
		} else
			return shoppingCart;
	}
	
	public static Order getOrderByPair(SellerUserPair pair){
		if (!shoppingCart.containsKey(pair)){
			shoppingCart.put(pair, new Order());
		}
		return shoppingCart.get(pair);
	}
}
