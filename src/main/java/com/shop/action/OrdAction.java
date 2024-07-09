package com.shop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.entity.Dtl;
import com.shop.model.entity.Ord;
import com.shop.model.entity.User;
import com.shop.service.DtlService;
import com.shop.service.OrdService;

@Component
public class OrdAction extends ActionSupport implements SessionAware {

	private Integer ordNo;
	private List<Ord> ordList = new ArrayList<Ord>();
	private Map<String, List<Dtl>> dtlMap = new HashMap<String, List<Dtl>>();
	private Ord ord;
	
	Map<String, Object> session;
	
	@Autowired
	OrdService ordSvc;
	
	@Autowired
	DtlService dtlSvc;
	
	public String query() {
		User user = (User) session.get("user");
		if(user == null) {
			return "returnLogin";
		}
		ordList = ordSvc.findByUser(user);
		System.out.println(ordList);
		
		for (Ord ord : ordList) {
			int ordNo = ord.getOrdNo();
			List<Dtl> dtlList =  dtlSvc.findByOrdNo(ordNo);
			dtlMap.put(String.valueOf(ordNo) , dtlList);
		}
		
//		dtlList = ordList.stream()
//			    .map(ord -> {
//			        Dtl dtl = new Dtl();
//			        dtl = (Dtl) dtlSvc.findByOrdNo(ord.getOrdNo());
//			        return dtl;
//			    })
//			    .filter(Objects::nonNull) 
//			    .collect(Collectors.toList());
		
		System.out.println(dtlMap);
		return "success";
	}
	
	public String showDatail() {
		ord = ordSvc.findByOrdNo(ordNo);
		return "success";
	}
	
	public Ord getOrd() {
		return ord;
	}

	public void setOrd(Ord ord) {
		this.ord = ord;
	}

	public List<Ord> getOrdList() {
		return ordList;
	}

	public void setOrdList(List<Ord> ordList) {
		this.ordList = ordList;
	}

	public Integer getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}

	
	public Map<String, List<Dtl>> getDtlMap() {
		return dtlMap;
	}

	public void setDtlMap(Map<String, List<Dtl>> dtlMap) {
		this.dtlMap = dtlMap;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
