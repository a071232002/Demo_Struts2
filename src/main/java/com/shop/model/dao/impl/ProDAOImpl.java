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

//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	@Resource(name = "sessionFactory")
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

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
