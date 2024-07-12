package com.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.shop.model.dto.DtlDTO;
import com.shop.model.dto.OrdDTO;
import com.shop.model.entity.Dtl;
import com.shop.model.entity.Pro;

public interface ManageService {
	
	public List<Pro> excelToProList(File proData) throws IOException;
	
	public int importProItem(List<Pro> proList);
	
	public List<Pro> getAddItems(int insertCount);
	
	public int deleteOrd(int ordNo);
	
	public List<OrdDTO> getAllOrd(); 
	
	public List<DtlDTO> dtlFormatToDTO(List<Dtl> dtlList);
	
	public int printOrdInfo(int ordNo);
	
	
	
}
