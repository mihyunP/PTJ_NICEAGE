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
	
	/**
	 * 회원의 주소 근처의 경로당 가져오기
	 * @return
	 */
	public List<Senior_Citizen_Center> findByAddress(String address) {
		return seniorRepository.findByAddress(address);
	}

}
