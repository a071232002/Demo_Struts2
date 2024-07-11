package com.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.DtlDAO;
import com.shop.model.dao.OrdDAO;
import com.shop.model.dao.ProDAO;
import com.shop.model.dto.OrdDTO;
import com.shop.model.entity.Pro;
import com.shop.service.ManageService;

@Component
public class ManageServiceImpl implements ManageService{
	
	@Autowired
	ProDAO proDAO;
	
	@Autowired
	OrdDAO ordDAO;
	
	@Autowired
	DtlDAO dtlDAO;
	
	

	@Override
	public List<OrdDTO> getAllOrd() {
		List<OrdDTO> list = new  ArrayList<OrdDTO>();
		
		
		
		return list;
	}
	
	
	@Override
	public List<Pro> getAddItems(int insertCount) {
		return proDAO.getAddItems(insertCount);
	}

	
	@Override
	public int deleteOrd(int ordNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int importProItem(List<Pro> proList) {		
		return proDAO.insertAll(proList);
	}

	@Override
	public int printOrdInfo(int ordNo) {
		// TODO Auto-generated method stub
		return 0;
	}


}
