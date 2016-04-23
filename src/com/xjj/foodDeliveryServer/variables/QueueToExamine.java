package com.xjj.foodDeliveryServer.variables;

import java.util.LinkedList;
import java.util.Queue;

import com.xjj.foodDeliveryServer.bean.Order;
import com.xjj.foodDeliveryServer.bean.Seller;

public class QueueToExamine {
	private static Queue<Seller> sellerQueue = null;
	private static Queue<Order>	orderQueue = null;

	public static Queue<Seller> getQueue() {
		if (sellerQueue == null) {
			sellerQueue = new LinkedList<>();
			return sellerQueue;
		} else {
			return sellerQueue;
		}
	}
	
	public static Queue<Order> orderQueue() {
		if (orderQueue == null) {
			orderQueue = new LinkedList<>();
			return orderQueue;
		} else {
			return orderQueue;
		}
	}
}
