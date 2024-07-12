package com.shop.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "pro")
@Component
public class Pro implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="proNo", updatable = false)
	private Integer proNo;
	
	@Column(name = "proName")
	private String proName;
	
	@Column(name = "proPrice")
	private Integer proPrice;
	
	@Column(name = "proQty")
	private Integer proQty;

	public Integer getProNo() {
		return proNo;
	}

	public void setProNo(Integer proNO) {
		this.proNo = proNO;
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
		return "Pro [proNo=" + proNo + ", proName=" + proName + ", proPrice=" + proPrice + ", proQty=" + proQty + "]";
	}

}
