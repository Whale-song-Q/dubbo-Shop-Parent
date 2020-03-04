package com.qhm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:hdshop-admin.properties")
public class AdminProperties {

	@Value("${admin.name}")
	String adminName;
	@Value("${admin.pwd}")
	String  password;
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Properties [adminName=" + adminName + ", password=" + password + "]";
	}
	public AdminProperties() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminProperties(String adminName, String password) {
		super();
		this.adminName = adminName;
		this.password = password;
	}
	
	
	
}
