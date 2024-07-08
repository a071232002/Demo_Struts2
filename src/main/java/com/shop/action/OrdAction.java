package com.shop.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.entity.Ord;
import com.shop.model.entity.User;
import com.shop.service.OrdService;

@Component
public class OrdAction extends ActionSupport implements SessionAware {

	private Integer ordNo;
	private Integer ordPrice;
	private byte ordSt;
	private List<Ord> ordList;
	
	Map<String, Object> session;
	
	@Autowired
	OrdService ordSvc;
	
	public String query() {
		User user = (User) session.get("user");
		if(user == null) {
			return "returnLogin";
		}
		ordList = ordSvc.findByUser(user);
		System.out.println(ordList);
		return "success";
	}
	
	public List<Ord> getOrdList() {
		return ordList;
	}

	public void setOrdList(List<Ord> ordList) {
		this.ordList = ordList;
	}

	public Integer getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}

	public Integer getOrdPrice() {
		return ordPrice;
	}

	public void setOrdPrice(Integer ordPrice) {
		this.ordPrice = ordPrice;
	}

	public byte getOrdSt() {
		return ordSt;
	}

	public void setOrdSt(byte ordSt) {
		this.ordSt = ordSt;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
