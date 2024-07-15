package com.shop.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.UserDAO;
import com.shop.model.entity.User;
import com.shop.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;

//	public UserDAO getDao() {
//		return dao;
//	}
//
//	@Resource(name = "userDAO")
//	public void setDao(UserDAO dao) {
//		this.dao = dao;
//	}

	@Override
	public int register(User user) {
		if (dao.findByMail(user.getUserMail()) != null) {
			return -1;
		}
		return dao.insert(user);
	}

	@Override
	public User update(User user) {
		dao.update(user);
		User userNew = dao.findByUserNo(user.getUserNo());
		System.out.println(userNew);
		return userNew;
	}

	@Override
	@Transactional
	public User login(String userMail, String userPsw) {
		User user = dao.findByMail(userMail.toLowerCase());
		if (user != null && user.getUserPsw().equals(userPsw)) {
			return user;
		} else {
			return null;
		}
	}

}
