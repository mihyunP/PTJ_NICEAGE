package com.ssafy.niceage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.User.User;

public class main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try{
			
			User user = new User();
			user.setAddress("123124");
			user.setBirth("2020-11-11");
			user.setEmergency("10123");
			user.setGender("M");
			user.setName("홍진빈");
			user.setId("jbhongg");
			user.setPassword("1234");
			user.setPhone("1234");
			em.persist(user);
			
			Board board = new Board();
			board.setTitle("asd");
			board.setContents("1243453254235");
			board.setDate("2020-08-04 11:22:22");
			board.setUser(user);
			
			em.persist(board);
			
			em.remove(user);
			
			tx.commit();
			
			
		}catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}

}
