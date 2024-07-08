package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.OrdDAO;
import com.shop.model.entity.Ord;
import com.shop.model.entity.User;
import com.shop.service.OrdService;

@Component("ordService")
public class OrdServiceImpl implements OrdService {
	
	@Autowired
	private OrdDAO ordDAO;

//	public OrdDAO getOrdDAO() {
//		return ordDAO;
//	}
//
//	@Resource(name = "ordDAO")
//	public void setOrdDAO(OrdDAO ordDAO) {
//		this.ordDAO = ordDAO;
//	}

	@Override
	public int add(Ord ord) {
		return ordDAO.insert(ord);
	}
	
	@Override
	public List<Ord> findByUser(User user) {
		return ordDAO.findByUser(user.getUserNo());
	}
	
	@Override
	public List<Ord> getAll() {
		return ordDAO.getAll();
	}

	@Override
	public Ord findByOrdNo(int OrdNo) {
		return ordDAO.findByOrdNo(OrdNo);
	}

	@Override
	public int update(Ord ord) {
		return ordDAO.update(ord);
	}



}
