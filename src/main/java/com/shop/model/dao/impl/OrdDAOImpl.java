package com.shop.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.OrdDAO;
import com.shop.model.entity.Ord;
import com.shop.model.entity.User;

import jakarta.annotation.Resource;

@Component("ordDAO")
@Transactional
public class OrdDAOImpl implements OrdDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public int insert(Ord ord) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(ord);
			return ord.getOrdNo();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Ord> findByUser(int userNo) {
		Session session = sessionFactory.getCurrentSession();
		List<Ord> list = new ArrayList<Ord>();
		try {
			String hql = "FROM Ord o WHERE o.user.userNo = :user ORDER BY o.ordNo DESC";
			list = session.createQuery(hql, Ord.class)
						  .setParameter("user", userNo)
						  .getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Ord> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Ord> list = new ArrayList<Ord>();
		try {
			list = session.createQuery("from Ord", Ord.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Ord findByOrdNo(int ordNo) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return session.get(Ord.class, ordNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int update(Ord ord) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(ord);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

}
