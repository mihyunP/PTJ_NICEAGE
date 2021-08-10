package com.ssafy.niceage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Enter.Enter;
import com.ssafy.niceage.Domain.User.User;

public interface EnterRepository extends JpaRepository<Enter, Long>{

	List<Enter> findByUser(User user);
}
