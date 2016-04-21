package com.xjj.foodDeliveryServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjj.foodDeliveryServer.bean.User;
import com.xjj.foodDeliveryServer.dao.UserDao;

@Service("userService")
public class UserService {
	@Autowired(required=true)
	private UserDao userDao;

	public User getUser(String tel, String password){
		User user = userDao.findUser(tel);
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
}