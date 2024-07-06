package com.shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.ProDAO;
import com.shop.model.dto.ProDTO;
import com.shop.model.entity.Pro;
import com.shop.service.ProService;

@Component("proService")
public class ProServiceImpl implements ProService {

	@Autowired
	ProDAO proDAO;

//	public ProDAO getProDAO() {
//		return proDAO;
//	}
//	
//	@Resource(name="proDAO")
//	public void setProDAO(ProDAO proDAO) {
//		this.proDAO = proDAO;
//	}

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

	public List<ProDTO> getDTOAll() {
		List<Pro> pros = proDAO.getAll();
		return pros.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private ProDTO convertToDto(Pro pro) {
		return new ProDTO(pro.getProNo(), pro.getProName(), pro.getProPrice(), pro.getProQty());
	}

}
