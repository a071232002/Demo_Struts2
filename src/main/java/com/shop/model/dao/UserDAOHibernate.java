package com.shop.model.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.entity.User;

@Component("userDAO")
public class UserDAOHibernate implements UserDAO {

//	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	@Autowired
	private SessionFactory sessionFactory;

//	@Resource(name = "sessionFactory")
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	@Override
	@Transactional
	public int insert(User user) {

		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(user);
			return user.getUserNo();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	@Transactional
	public int update(User user) {

		Session session = sessionFactory.getCurrentSession();

		try {
			session.update(user);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	@Transactional
	public User findByMail(String userMail) {

		Session session = sessionFactory.getCurrentSession();

		try {

			return session.get(User.class, userMail);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
