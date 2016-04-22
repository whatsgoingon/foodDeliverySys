package com.xjj.foodDeliveryServer.variables;

import java.util.LinkedList;
import java.util.Queue;

import com.xjj.foodDeliveryServer.bean.Seller;

public class QueueToExamine {
	private static Queue<Seller> queueToExamine = null;

	public static Queue<Seller> getQueue() {
		if (queueToExamine == null) {
			queueToExamine = new LinkedList<>();
			return queueToExamine;
		} else {
			return queueToExamine;
		}
	}
}
