package com.shop.model.dao;

import com.shop.model.entity.User;

public interface UserDAO {

	public int insert(User user);

	public int update(User user);
	
	public User findByUserNo(int userNo);
	
	public User findByMail(String userMail);
	
	
}
