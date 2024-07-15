package com.shop.model.dao;

import java.util.List;

import com.shop.model.entity.Ord;
import com.shop.model.entity.User;

public interface OrdDAO {
	public int insert(Ord ord);
	
	public List<Ord> getAll();
	
	public List<Ord> findByUser(int userNo);
	
	public Ord findByOrdNo(int ordNo);
	
	public int update(Ord ord);
	
	public void delete(Ord ord);
}
