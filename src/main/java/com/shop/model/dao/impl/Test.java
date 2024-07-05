package com.shop.model.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		String[] s = context.getBeanDefinitionNames();
		for (String name : s) {
			System.out.println(name);
		}	
	}
}
