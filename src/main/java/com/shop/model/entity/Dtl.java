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
	@JoinColumn(name = "ordNo", referencedColumnName = "ordNo")
	private Ord ord;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proNo", referencedColumnName = "proNo")
	private Pro pro;
	
	@Column(name = "dtlQty")
	private Integer dtlQty;
	
	@Column(name = "dtlPrice")
	private Integer dtlPrice;
		
	
	public Dtl() {

	}

	public Dtl(Ord ord, Pro pro, Integer dtlQty, Integer dtlPrice) {
		super();
		this.ord = ord;
		this.pro = pro;
		this.dtlQty = dtlQty;
		this.dtlPrice = dtlPrice;
	}

	public Integer getDtlNo() {
		return dtlNo;
	}

	public void setDtlNo(Integer dtlNo) {
		this.dtlNo = dtlNo;
	}

	public Ord getOrd() {
		return ord;
	}

	public void setOrd(Ord ord) {
		this.ord = ord;
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
		return "Dtl [dtlNo=" + dtlNo + ", ord=" + ord + ", pro=" + pro + ", dtlQty=" + dtlQty + ", dtlPrice=" + dtlPrice
				+ "]";
	}

}
