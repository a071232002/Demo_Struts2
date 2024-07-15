package com.shop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name ="user")
@Component
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="userNo", updatable = false)
	private Integer userNo;
	
	@Column(name = "userMail", unique = true, updatable = false)
	private String userMail;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userPsw")
	private String userPsw;
	

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
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

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userMail=" + userMail + ", userName=" + userName + ", userPsw=" + userPsw
				+ "]";
	}

}
