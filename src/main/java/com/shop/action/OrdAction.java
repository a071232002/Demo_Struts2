package com.shop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class OrdAction extends ActionSupport implements SessionAware {

	private Integer ordNo;
	private Integer ordPrice;
	private byte ordSt;
	
	
	Map<String, Object> session;

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
