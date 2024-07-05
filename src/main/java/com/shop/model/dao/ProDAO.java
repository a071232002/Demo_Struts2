package com.shop.model.dao;

import java.util.List;

import com.shop.model.entity.Pro;

public interface ProDAO {
	
	public int insert(Pro pro);
	
	public List<Pro> getAll();
	
	public int update(Pro pro);
	
}
