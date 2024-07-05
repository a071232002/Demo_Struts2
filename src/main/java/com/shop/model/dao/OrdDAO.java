package com.shop.model.dao;

import java.util.List;

import com.shop.model.entity.Ord;

public interface OrdDAO {
	public int insert(Ord ord);
	
	public List<Ord> getAll();
	
	public Ord findByOrdNo(int ordNo);
	
	public int update(Ord ord);
}
