package com.xjj.foodDeliveryServer.variables;

import java.util.HashMap;
import java.util.Map;

import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.SellerUserPair;

public class ShoppingCart {
	private static Map<SellerUserPair, Order> shoppingCartMap = null;

	public static Map<SellerUserPair, Order> getShoppingCartMap() {
		if (shoppingCartMap == null) {
			shoppingCartMap = new HashMap<>();
			return shoppingCartMap;
		} else
			return shoppingCartMap;
	}
	
	public static void setShoppingCartMap(Map<SellerUserPair, Order> shoppingCartMap) {
		ShoppingCart.shoppingCartMap = shoppingCartMap;
	}
	
//	public static Order getOrderByPair(SellerUserPair pair){
//		if (shoppingCart == null) {
//			shoppingCart = new HashMap<>();
//		}
//		if (!shoppingCart.containsKey(pair)){
//			shoppingCart.put(pair, new Order());
//		}
//		return shoppingCart.get(pair);
//	}
}
