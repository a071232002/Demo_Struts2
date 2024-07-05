package com.shop.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static final SessionFactory sessionFactory = createSessionFactory();

	private static SessionFactory createSessionFactory() {
		try {
			registry = new StandardServiceRegistryBuilder().configure().build();

			SessionFactory sessionFactroy = new MetadataSources(registry).buildMetadata().buildSessionFactory();

			return sessionFactroy;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
