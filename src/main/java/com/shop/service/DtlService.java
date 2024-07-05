package com.shop.service;

import java.util.List;

import com.shop.model.entity.Dtl;

public interface DtlService {
	
	public int add(List<Dtl> dtlList);
	
	public List<Dtl> findByOrdNo(int OrdNo);
	
}
