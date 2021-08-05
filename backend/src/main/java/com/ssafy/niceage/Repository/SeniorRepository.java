package com.ssafy.niceage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;

public interface SeniorRepository extends JpaRepository<Senior_Citizen_Center, Integer>{

	Senior_Citizen_Center findByseniorId(Integer id);
}