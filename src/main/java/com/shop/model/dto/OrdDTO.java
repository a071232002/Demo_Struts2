package com.shop.model.dto;

import java.util.List;

public class OrdDTO {
	
	private int ordNo;
	
	private int userNo;
	
	private int ordPrice;
	
	private String ordSt;
	
	private List<DtlDTO> dtlDTOList;

	
	public OrdDTO(int ordNo, int userNo, int ordPrice, byte ordSt, List<DtlDTO> dtlDTOList) {
		super();
		this.ordNo = ordNo;
		this.userNo = userNo;
		this.ordPrice = ordPrice;
		this.ordSt = getOrdStatusText(ordSt);
		this.dtlDTOList = dtlDTOList;
	}

	public int getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getOrdPrice() {
		return ordPrice;
	}

	public void setOrdPrice(int ordPrice) {
		this.ordPrice = ordPrice;
	}

	public String getOrdSt() {
		return ordSt;
	}

	public void setOrdSt(byte ordSt) {
		this.ordSt = getOrdStatusText(ordSt);
	}

	public List<DtlDTO> getDtlDTOList() {
		return dtlDTOList;
	}

	public void setDtlDTOList(List<DtlDTO> dtlDTOList) {
		this.dtlDTOList = dtlDTOList;
	}
	
	
	private String getOrdStatusText(byte ordSt) {
	        switch (ordSt) {
	            case 0:
	                return "未出貨";
	            case 1:
	                return "已出貨";
	            case 9:
	                return "退貨";
	            default:
	                return "其他狀態";
	      }
	}
	
	@Override
	public String toString() {
		return "OrdDTO [ordNo=" + ordNo + ", userNo=" + userNo + ", ordPrice=" + ordPrice + ", ordSt=" + ordSt
				+ ", dtlDTOList=" + dtlDTOList + "]";
	}
	
	
	
	
	
}
