package com.shop.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.dto.CartDTO;
import com.shop.model.entity.User;
import com.shop.service.DtlService;
import com.shop.service.OrdService;
import com.shop.service.ProService;

@Component
public class CartAction extends ActionSupport implements SessionAware {

	private Integer userNo;
	private Integer proNo;
	private String proName;
	private Integer proQty;
	private Integer proPrice;

	private List<CartDTO> cartList = new ArrayList<CartDTO>();

	Map<String, Object> session;

	@Autowired
	OrdService ordScv;

	@Autowired
	ProService proSvc;

	@Autowired
	DtlService dtlScv;

	public List<CartDTO> getCartList() {
		return cartList;
	}

	@SuppressWarnings("unchecked")
	public void setCartList() {
		this.cartList = (List<CartDTO>) session.getOrDefault("cart", cartList);
	}

	public String add() {
		setCartList();
		if (cartList != null) {
			for (CartDTO cartDTO : cartList) {
				if (cartDTO.getProNo() == proNo) {
					cartDTO.setOrdQty(cartDTO.getOrdQty() + proQty);
					return "success";
				} 
			}
		}
		System.out.println("add new item");
		CartDTO cartDTO = new CartDTO(proNo, proName, proQty, proPrice);
		cartList.add(cartDTO);
		session.put("cart", cartList);
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String query() {
		User user = (User) session.get("user");
		if(user == null) {
			return "returnLogin";
		}
		cartList = (List<CartDTO>) session.get("cart");
		System.out.println(cartList);
		return "success";
	}

	public String update() {
		return "success";
	}

	public String remove() {
		return "success";
	}
	
	public String confirmOrder() {
		return "success";
	}
	
	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
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

	public Integer getProQty() {
		return proQty;
	}

	public void setProQty(Integer proQty) {
		this.proQty = proQty;
	}

	public Integer getProPrice() {
		return proPrice;
	}

	public void setProPrice(Integer proPrice) {
		this.proPrice = proPrice;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
