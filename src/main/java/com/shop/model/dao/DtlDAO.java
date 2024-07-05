package com.shop.model.dao;

import java.util.List;

import com.shop.model.entity.Dtl;

public interface DtlDAO {
	
	public int insert(List<Dtl> dtlList);
	
	public List<Dtl> findByOrdNo(int ordNo);
}
