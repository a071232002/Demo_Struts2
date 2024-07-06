package com.shop.model.dto;

public class ProDTO {

	private Integer proNo;

	private String proName;

	private Integer proPrice;

	private Integer proQty;

	public ProDTO(Integer proNo, String proName, Integer proPrice, Integer proQty) {
		super();
		this.proNo = proNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proQty = proQty;
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

	@Override
	public String toString() {
		return "ProDTO [proNo=" + proNo + ", proName=" + proName + ", proPrice=" + proPrice + ", proQty=" + proQty
				+ "]";
	}
}
