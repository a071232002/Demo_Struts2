package com.shop.service;

import java.util.List;

import com.shop.model.entity.Ord;
import com.shop.model.entity.User;

public interface OrdService {
	
	public int add(Ord ord);
	
	public List<Ord> getAll();
	
	public List<Ord> findByUser(User user); 
	
	public Ord findByOrdNo(int OrdNo);
	
	public int update(Ord ord);
}
