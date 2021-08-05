package com.ssafy.niceage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;

public interface SeniorRepository extends JpaRepository<Senior_Citizen_Center, Integer>{

	List<Senior_Citizen_Center> findByAddress(String address);
	
}