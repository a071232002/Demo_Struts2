package com.shop.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shop.model.dao.UserDAO;
import com.shop.model.entity.User;

@Component("userService")
public class UserServiceImpl implements UserService {

	private UserDAO dao;

	public UserDAO getDao() {
		return dao;
	}

	@Resource(name = "userDAO")
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@Override
	public int register(User user) {
		return dao.insert(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public User login(String userMail, String userPsw) {
		User user = dao.findByMail(userMail.trim());
		if (user != null && user.getUserPsw().equals(userPsw)) {
			return user;
		} else {
			return null;
		}
	}

}
