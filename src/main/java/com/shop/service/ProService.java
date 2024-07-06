package com.shop.service;

import java.util.List;

import com.shop.model.dto.ProDTO;
import com.shop.model.entity.Pro;

public interface ProService {
	
	public int add(Pro pro);
	
	public int update(Pro pro);
	
	public List<Pro> getAll();
	
	public List<ProDTO> getDTOAll();
}
