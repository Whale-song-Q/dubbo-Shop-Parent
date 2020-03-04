package com.qhm.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.qhm.config.AdminProperties;
import com.qhm.service.UserService;

@Service(interfaceClass=UserService.class,version="1.0.0")
public class UserServiceImpl implements UserService{

	
	@Autowired
	AdminProperties adminProperties;
	
	
	@Override
	public boolean login(String userName, String passWord) {
		// TODO Auto-generated method stub
		return adminProperties.getAdminName().equals(userName) && adminProperties.getPassword().equals(passWord);
		
	}
	
	
}
