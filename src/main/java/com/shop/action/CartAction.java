package com.shop.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.dto.CartDTO;
import com.shop.model.entity.Dtl;
import com.shop.model.entity.Ord;
import com.shop.model.entity.Pro;
import com.shop.model.entity.User;
import com.shop.service.DtlService;
import com.shop.service.OrdService;
import com.shop.service.ProService;

@Component
@Scope("prototype")
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
		System.out.println("=====================這是Action add=======================");
		setCartList();
		if (cartList != null) {
			for (CartDTO cartDTO : cartList) {
				if (cartDTO.getProNo() == proNo) {
					cartDTO.setOrdQty(cartDTO.getOrdQty() + proQty);
					return "success";
				}
			}
		}
		System.out.println("=======================add new item=======================");
		CartDTO cartDTO = new CartDTO(proNo, proName, proQty, proPrice);
		cartList.add(cartDTO);
		session.put("cart", cartList);
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String query() {
		User user = (User) session.get("user");
		if (user == null) {
			return "returnLogin";
		}
		setCartList();
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

		Ord ord = new Ord();
		ord.setUser((User) session.get("user"));
		ord.setOrdPrice(orderAmount());
		int result = ordScv.add(ord, convertToDtl());

		if (result == 1) {
			session.remove("cart");
			System.out.println("成功");
		}
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

	public int orderAmount() {
		int count = 0;
		setCartList();
		if (cartList != null) {
			for (CartDTO cartDTO : cartList) {
				int qty = cartDTO.getOrdQty();
				int price = cartDTO.getOrdPrice();
				int itemTotal = qty * price;
				count += itemTotal;
			}
		}
		return count;
	}

	public List<Dtl> convertToDtl() {
		List<Dtl> dtlList = new ArrayList<Dtl>();
		setCartList();
		if (cartList != null) {
			dtlList = cartList.stream().map(item -> {
				Pro pro = proSvc.findByProNo(item.getProNo());
				return new Dtl(null, pro, item.getOrdQty(), item.getOrdPrice());
			}).collect(Collectors.toList());
		}
		return dtlList;
	}

}
