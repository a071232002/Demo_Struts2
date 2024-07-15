package com.shop.model.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.DtlDAO;
import com.shop.model.entity.Dtl;

import jakarta.annotation.Resource;

@Component("dtlDAO")
@Transactional
public class DtlDAOImpl implements DtlDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public int insert(List<Dtl> dtlList) {
		Session session = sessionFactory.getCurrentSession();

		try {
			for (Dtl dtl : dtlList) {
				session.save(dtl);
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Dtl> findByOrdNo(int ordNo) {
		Session session = sessionFactory.getCurrentSession();

		try {

			List<Dtl> list = session.createQuery("from Dtl where ordNo = :ordNo", Dtl.class)
								    .setParameter("ordNo", ordNo).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Dtl dtl) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(dtl);
	}

}
