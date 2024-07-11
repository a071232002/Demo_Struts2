package com.shop.service;

import java.util.List;

import com.shop.model.dto.OrdDTO;
import com.shop.model.entity.Pro;

public interface ManageService {
	
	public int deleteOrd(int ordNo);
	
	public List<OrdDTO> getAllOrd(); 
	
	public List<Pro> getAddItems(int insertCount);
	
	public int importProItem(List<Pro> proList);
	
	public int printOrdInfo(int ordNo);
	
}
