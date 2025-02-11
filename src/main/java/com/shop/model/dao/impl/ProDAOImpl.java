package com.shop.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.ProDAO;
import com.shop.model.entity.Pro;

@Component("proDAO")
@Transactional
public class ProDAOImpl implements ProDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insert(Pro pro) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(pro);
			return pro.getProNo();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}
	
	@Override
	public int insertAll(List<Pro> proList) {
		Session session = sessionFactory.getCurrentSession();
		int insertCount = 0;
		int batchSize = 20;
	    
		try {
			
//			for (Pro pro : proList) {
//				session.save(pro);
//				insertCount++;
//			}
			  for (int i = 0; i < proList.size(); i++) {
		            session.save(proList.get(i));
		            insertCount++;
		            
		            if (i > 0 && i % batchSize == 0) {
		                session.flush();
		                session.clear();
		            }
			  }
			return insertCount;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}
	
	@Override
	public Pro findByProNo(int proNo) {
		Session session = sessionFactory.getCurrentSession();
		Pro pro = null;
		try {
			pro = session.get(Pro.class, proNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pro;
	}
	
	@Override
	public List<Pro> getAddItems(int insertCount){
		Session session = sessionFactory.getCurrentSession();
		List<Pro> list = new ArrayList<Pro>();
		try {
			String hql = "FROM Pro ORDER BY ProNo DESC";
	        list = session.createQuery(hql, Pro.class)
	                      .setMaxResults(insertCount)
	                      .getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public List<Pro> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Pro> list = new ArrayList<Pro>();
		try {
			list = session.createQuery("from Pro", Pro.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int update(Pro pro) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(pro);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
