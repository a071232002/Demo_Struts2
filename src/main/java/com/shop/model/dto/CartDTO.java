package com.shop.model.dto;

public class CartDTO {

	private Integer proNo;
	private String proName;
	private Integer ordQty;
	private Integer ordPrice;
	
	public CartDTO(Integer proNo, String proName, Integer ordQty, Integer ordPrice) {
		super();
		this.proNo = proNo;
		this.proName = proName;
		this.ordQty = ordQty;
		this.ordPrice = ordPrice;
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

	public Integer getOrdPrice() {
		return ordPrice;
	}

	public void setOrdPrice(Integer ordprice) {
		this.ordPrice = ordprice;
	}

	@Override
	public String toString() {
		return "CartDTO [proNo=" + proNo + ", proName=" + proName + ", ordQty=" + ordQty + ", ordPrice=" + ordPrice
				+ "]";
	}
	
	
}
