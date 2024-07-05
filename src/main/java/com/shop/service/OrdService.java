package com.shop.service;

import java.util.List;

import com.shop.model.entity.Ord;

public interface OrdService {
	
	public int add(Ord ord);
	
	public List<Ord> getAll();
	
	public Ord findByOrdNo(int OrdNo);
	
	public int update(Ord ord);
}
