package com.shop.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.entity.Pro;
import com.shop.service.ProService;

@Component
public class ShopAction extends ActionSupport implements SessionAware {
	
	private List<Pro> proList;
	private Integer proNo;
	private String proName;
	private Integer proPrice;
	private Integer proQty;
	
	private Map<String, Object> session;
	
	
	@Resource(name="proService")
	private ProService proSvc;
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public String getAll() {
		proList = proSvc.getAll();	
		System.out.println(proList);
		return "success";
	}

	public Integer getProNo() {
		return proNo;
	}

	public void setProNo(Integer proNo) {
		this.proNo = proNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getProPrice() {
		return proPrice;
	}

	public void setProPrice(Integer proPrice) {
		this.proPrice = proPrice;
	}

	public Integer getProQty() {
		return proQty;
	}

	public void setProQty(Integer proQty) {
		this.proQty = proQty;
	}
	
	
}
