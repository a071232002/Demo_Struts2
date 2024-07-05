package com.shop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware {

	private Integer proNo;
	private Integer userNo;
	private Integer dtlQty;
	private Integer dtlPrice;

	Map<String, Object> session;

	public String add() {
		return "success";
	}

	public String update() {
		return "success";
	}

	public String remove() {
		return "success";
	}

	public String order() {
		return "success";
	}

	public Integer getProNo() {
		return proNo;
	}

	public void setProNo(Integer proNo) {
		this.proNo = proNo;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public Integer getDtlQty() {
		return dtlQty;
	}

	public void setDtlQty(Integer dtlQty) {
		this.dtlQty = dtlQty;
	}

	public Integer getDtlPrice() {
		return dtlPrice;
	}

	public void setDtlPrice(Integer dtlPrice) {
		this.dtlPrice = dtlPrice;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	@Override
	public String toString() {
		return "CartAction [proNo=" + proNo + ", userNo=" + userNo + ", dtlQty=" + dtlQty + ", dtlPrice=" + dtlPrice
				+ ", session=" + session + "]";
	}
	
	
}
