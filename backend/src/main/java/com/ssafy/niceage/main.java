package com.ssafy.niceage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ssafy.niceage.Domain.User.User;

public class main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		User user = new User();
		user.setBirth("2020-01-01");
		user.setEmergency("010-123-1234");
		user.setGender("F");
		user.setId("jbhongg");
		user.setName("홍진빈");
		user.setPassword("12341234");
		user.setPhone("010-1234-3433");
		user.setAddress("asdasd");
		
		em.persist(user);
		
		tx.commit();
		em.close();
		
		emf.close();
	}

}
