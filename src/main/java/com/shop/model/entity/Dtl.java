package com.shop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "dtl")
@Component
public class Dtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dtlNo", updatable = false)
	private Integer dtlNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userNo", referencedColumnName = "userNo")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proNo", referencedColumnName = "proNo")
	private Pro pro;
	
	@Column(name = "dtlQty")
	private Integer dtlQty;
	
	@Column(name = "dtlPrice")
	private Integer dtlPrice;

	public Integer getDtlNo() {
		return dtlNo;
	}

	public void setDtlNo(Integer dtlNo) {
		this.dtlNo = dtlNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pro getPro() {
		return pro;
	}

	public void setPro(Pro pro) {
		this.pro = pro;
	}

	public Integer getDtlQty() {
		return dtlQty;
	}

	public void setDtlQty(Integer dtlQty) {
		this.dtlQty = dtlQty;
	}

	public Integer getDtlPrice() {
		return dtlPrice;
	}

	public void setDtlPrice(Integer dtlPrice) {
		this.dtlPrice = dtlPrice;
	}

	@Override
	public String toString() {
		return "Dtl [dtlNo=" + dtlNo + ", user=" + user + ", pro=" + pro + ", dtlQty=" + dtlQty + ", dtlPrice="
				+ dtlPrice + "]";
	}

}
