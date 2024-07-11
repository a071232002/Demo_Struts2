package com.shop.model.dao;

import java.util.List;

import com.shop.model.entity.Pro;

public interface ProDAO {
	
	public int insert(Pro pro);
	
	public int insertAll(List<Pro> proList);
	
	public List<Pro> getAll();
	
	public List<Pro> getAddItems(int insertCount);
	
	public Pro findByProNo(int proNo);
	
	public int update(Pro pro);
	
}
