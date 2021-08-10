package com.ssafy.niceage.Service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Domain.Enter.Enter;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Repository.SeniorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeniorService {
	
	private final SeniorRepository seniorRepository;

	public Senior_Citizen_Center findBySeniorId(Long seniorId) {
		return seniorRepository.findBySeniorId(seniorId);
	}

	public List<Senior_Citizen_Center> findBySeniorAddress(String userAddress) {
		return seniorRepository.findBySeniorAddress(userAddress);
	}

//	public List<Senior_Citizen_Center> frequentSeniorList(List<Enter> enterList) {
//		List<Long> seniorIdList = seniorRepository.findByEnter(enterList);
//		List<Senior_Citizen_Center> list = new LinkedList<Senior_Citizen_Center>();
//		for (int i = 0; i < seniorIdList.size(); i++) {
//			list.add(seniorRepository.findBySeniorId(seniorIdList.get(i)));
//			
//		}
////		List<Long> seniorIdList = new LinkedList<>();
////		for (int i = 0; i < seniorIdList.size(); i++) {
////			enterList.get(i).
////		}
////		return seniorRepository.findBySeniorId();
//	}


}