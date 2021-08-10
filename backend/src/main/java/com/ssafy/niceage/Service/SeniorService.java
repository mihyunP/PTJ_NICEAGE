package com.ssafy.niceage.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	public List<Senior_Citizen_Center> findBySeniorAddress(String address) {
		String[] arr = address.split(" ");
		String userAddress = new String();
		for (int i = 0; i < 2; i++) {
			if (arr[i].equals("서울") || arr[i].equals("서울시")) {
				arr[i] = "서울특별시";
			}
			userAddress += arr[i] + " ";
		}
		return seniorRepository.findBySeniorAddress(userAddress);
	}

	/**
	 * 자주가는 경로당 찾기위한 메소드
	 * 반환되는 리스트는 내림차순으로 정렬되어 있음
	 * @param frequentSenior
	 * @return
	 */
	public List<Senior_Citizen_Center> frequentSeniorList(List<Enter> frequentSenior) {
		Map<Long, Long> map = new HashMap<>();
		Long[] enterArr = new Long[frequentSenior.size()];
		for (int i = 0; i < enterArr.length; i++) {
			enterArr[i] = frequentSenior.get(i).getSenior().getSeniorId();
		}
		
		Arrays.sort(enterArr);
		
		long count = 1;
		for (int i = 1; i < enterArr.length; i++) {			
			if (enterArr[i - 1] == enterArr[i]) {
				count++;
			} else {
				map.put(enterArr[i - 1], count);
				count = 1;
			}
			if (i == (enterArr.length - 1)) {
				map.put(enterArr[i], count);
			}
		}
		
		List<Map.Entry<Long, Long>> mapList = new LinkedList<>(map.entrySet());
		mapList.sort(Map.Entry.comparingByValue());
		
		List<Senior_Citizen_Center> resultList = new LinkedList<>();
		for (int i = mapList.size() - 1; i >= 0; i--) {
			long seniorId = mapList.get(i).getKey();
			Senior_Citizen_Center senior = seniorRepository.findBySeniorId(seniorId);
			resultList.add(senior);
		}
		
		return resultList;
	}

}