package com.shop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.entity.User;
import com.shop.service.UserService;

@Component
public class UserAction extends ActionSupport implements SessionAware {
	private int userNo;
	private String userMail;
	private String userName;
	private String userPsw;
	private Map<String, Object> session;

	@Autowired
	private UserService userSvc;

//	public UserService getUserSvc() {
//		return userSvc;
//	}
//
//	@Resource(name = "userService")
//	public void setUserSvc(UserService userSvc) {
//		this.userSvc = userSvc;
//	}

	public String register() {
		User user = new User();
		user.setUserMail(userMail.toLowerCase().trim());
		user.setUserName(userName.trim());
		user.setUserPsw(userPsw.trim());

		user.setUserNo(userSvc.register(user));

//		DAO JDBC
//		user.setUserNo(dao.add(user));

		session.put("user", user);
		return "success";
	}

	public String login() {
		User user = userSvc.login(userMail, userPsw);
		if (user != null) {
//		登入後, 改將使用者資料丟入session
			session.put("user", user);
			session.remove("error");
			return "success";
		} else {
			session.put("error", "帳號密碼錯誤! 請重試");
			return "error";
		}
	}

	public String goToMemPage() {
		User user = (User) session.get("user");
		if (user == null) {
			return "returnLogin";
		}
		return "success";
	}

	public String update() {
		User user = new User();
		user.setUserMail(userMail.toLowerCase().trim());
		user.setUserName(userName);
		user.setUserPsw(userPsw);
		user.setUserNo(((User) session.get("user")).getUserNo());
		userSvc.update(user);
		session.put("user", user);
		return "success";
	}

	public String logout() {
		if (session != null) {
			session.clear();
		}
		return "success";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPsw() {
		return userPsw;
	}

	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
