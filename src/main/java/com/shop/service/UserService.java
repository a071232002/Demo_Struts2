package com.shop.service;

import com.shop.model.entity.User;

public interface UserService {

	public int register(User user);

	public void update(User user);

	public User login(String userMail, String userPsw);

}
