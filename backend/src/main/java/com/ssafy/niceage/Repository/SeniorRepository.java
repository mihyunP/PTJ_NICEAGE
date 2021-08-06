package com.ssafy.niceage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;

public interface SeniorRepository extends JpaRepository<Senior_Citizen_Center, Integer>{

	Senior_Citizen_Center findByseniorId(Integer id);

	@Query("SELECT s FROM Senior_Citizen_Center s WHERE s.seniorAddress like %:userAddress%")
	List<Senior_Citizen_Center> findByseniorAddress(@Param("userAddress") String userAddress);

}