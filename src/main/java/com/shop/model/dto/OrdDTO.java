package com.shop.model.dto;

import java.util.List;

public class OrdDTO {
	
	private int ordNo;
	
	private int userNo;
	
	private int ordPrice;
	
	private byte ordSt;
	
	private List<DtlDTO> dtlDTOList;

	
	public OrdDTO(int ordNo, int userNo, int ordPrice, byte ordSt, List<DtlDTO> dtlDTOList) {
		super();
		this.ordNo = ordNo;
		this.userNo = userNo;
		this.ordPrice = ordPrice;
		this.ordSt = ordSt;
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

	public byte getOrdSt() {
		return ordSt;
	}

	public void setOrdSt(byte ordSt) {
		this.ordSt = ordSt;
	}

	public List<DtlDTO> getDtlDTOList() {
		return dtlDTOList;
	}

	public void setDtlDTOList(List<DtlDTO> dtlDTOList) {
		this.dtlDTOList = dtlDTOList;
	}
	
	
	
	
	
}
