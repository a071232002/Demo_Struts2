package com.shop.model.dto;

public class DtlDTO {
	
	private int proNo;
	
	private String proName;
	
	private int ordQty;
	
	private int ordPrice;

	
	public DtlDTO(int proNo, String proName, int ordQty, int ordPrice) {
		super();
		this.proNo = proNo;
		this.proName = proName;
		this.ordQty = ordQty;
		this.ordPrice = ordPrice;
	}


	public int getProNo() {
		return proNo;
	}


	public void setProNo(int proNo) {
		this.proNo = proNo;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}


	public int getOrdQty() {
		return ordQty;
	}


	public void setOrdQty(int ordQty) {
		this.ordQty = ordQty;
	}


	public int getOrdPrice() {
		return ordPrice;
	}


	public void setOrdPrice(int ordPrice) {
		this.ordPrice = ordPrice;
	}


	@Override
	public String toString() {
		return "DtlDTO [proNo=" + proNo + ", proName=" + proName + ", ordQty=" + ordQty + ", ordPrice=" + ordPrice
				+ "]";
	}

	
	
}
