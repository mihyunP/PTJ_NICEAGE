package com.ssafy.niceage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.User.User;

import antlr.collections.List;

public interface UserRepository extends JpaRepository<User, Integer>{

	public static List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public static User create(User user) {
		// TODO Auto-generated method stub
		return null;
		
	}

}
