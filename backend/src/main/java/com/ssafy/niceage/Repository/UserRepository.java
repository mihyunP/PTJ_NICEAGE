package com.ssafy.niceage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.niceage.Domain.User.User;

import antlr.collections.List;

public interface UserRepository extends JpaRepository<User, Integer>{
	 User findByUId(String Id);
	 User findByUIdAndUPassword(String Id, String Password);
}
