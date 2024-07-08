package com.shop.model.dto;

public class CartDTO {

	private Integer proNo;
	private String proName;
	private Integer ordQty;
	private Integer ordprice;
	
	public CartDTO(Integer proNo, String proName, Integer ordQty, Integer ordprice) {
		super();
		this.proNo = proNo;
		this.proName = proName;
		this.ordQty = ordQty;
		this.ordprice = ordprice;
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

	public Integer getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(Integer ordQty) {
		this.ordQty = ordQty;
	}

	public Integer getOrdprice() {
		return ordprice;
	}

	public void setOrdprice(Integer ordprice) {
		this.ordprice = ordprice;
	}

	@Override
	public String toString() {
		return "CartDTO [proNo=" + proNo + ", proName=" + proName + ", ordQty=" + ordQty + ", ordprice=" + ordprice
				+ "]";
	}
	
	
}
