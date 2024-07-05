package com.shop.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shop.model.dao.OrdDAO;
import com.shop.model.entity.Ord;

@Component("ordService")
public class OrdServiceImpl implements OrdService {

	private OrdDAO ordDAO;

	public OrdDAO getOrdDAO() {
		return ordDAO;
	}

	@Resource(name = "ordDAO")
	public void setOrdDAO(OrdDAO ordDAO) {
		this.ordDAO = ordDAO;
	}

	@Override
	public int add(Ord ord) {
		return ordDAO.insert(ord);
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
