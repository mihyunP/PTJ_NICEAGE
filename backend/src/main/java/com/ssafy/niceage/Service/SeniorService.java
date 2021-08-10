package com.ssafy.niceage.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Repository.SeniorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeniorService {
	
	private final SeniorRepository seniorRepository;

	public Senior_Citizen_Center findByseniorId(Long id) {
		return seniorRepository.findByseniorId(id);
	}

	public List<Senior_Citizen_Center> findByseniorAddress(String userAddress) {
		return seniorRepository.findByseniorAddress(userAddress);
	}

}