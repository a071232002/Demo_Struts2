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
@Table(name = "ord")
@Component
public class Ord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordNo", updatable = false)
	private Integer ordNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userNo", referencedColumnName = "userNo")
	private User user;

	@Column(name = "ordPrice")
	private Integer ordPrice;

	@Column(name = "ordSt")
	private byte ordSt;

	public Integer getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getOrdPrice() {
		return ordPrice;
	}

	public void setOrdPrice(Integer ordPrice) {
		this.ordPrice = ordPrice;
	}

	public byte getOrdSt() {
		return ordSt;
	}

	public void setOrdSt(byte ordSt) {
		this.ordSt = ordSt;
	}

	@Override
	public String toString() {
		return "Ord [ordNo=" + ordNo + ", user=" + user + ", ordPrice=" + ordPrice + ", ordSt=" + ordSt + "]";
	}

}
