package com.ssafy.niceage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.niceage.Domain.User.User;

import antlr.collections.List;

public interface UserRepository extends JpaRepository<User, Long>{
	 User findByUserId(String Id);
	 User findByUserIdAndUserPassword(String Id, String Password);
	 User findByUserPhone(String userPhone);
}
