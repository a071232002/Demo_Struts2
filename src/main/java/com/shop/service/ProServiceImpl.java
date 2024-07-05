package com.shop.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shop.model.dao.ProDAO;
import com.shop.model.entity.Pro;

@Component("proService")
public class ProServiceImpl implements ProService{
	
	ProDAO proDAO;
	
	public ProDAO getProDAO() {
		return proDAO;
	}
	
	@Resource(name="proDAO")
	public void setProDAO(ProDAO proDAO) {
		this.proDAO = proDAO;
	}

	@Override
	public int add(Pro pro) {
		return proDAO.insert(pro);
	}

	@Override
	public int update(Pro pro) {
		return proDAO.update(pro);
	}

	@Override
	public List<Pro> getAll() {
		return proDAO.getAll();
	}

}
