package com.xjj.foodDeliveryServer.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
	public static String md5Encoder(String string){
		return DigestUtils.md5Hex(string);
	}
}
