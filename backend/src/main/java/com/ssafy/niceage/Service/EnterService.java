package com.ssafy.niceage.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Domain.Enter.Enter;
import com.ssafy.niceage.Domain.Enter.EnterDTO;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Repository.EnterRepository;
import com.ssafy.niceage.Repository.SeniorRepository;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnterService {

	private final EnterRepository enterRepository;
	private final SeniorRepository seniorRepository;
	
	public void save(EnterDTO enterDto) {
		Enter enter = enterDto.toEntity();
		enterRepository.save(enter);
	}

	/**
	 * 경로당 서비스 클릭시 자주가는 경로당을 반환하기 위해 userid에 해당하는 모든 enter entity를 가져옴
	 * @param user
	 * @return
	 */
	public List<Enter> findByUser(User user) {
		return enterRepository.findByUser(user);
	}

	/**
	 * 경로당 서비스 클릭시 응답으로 경로당 리스트와 자주가는 경로당을 한번에 보내주기 위한 자료구조 merge
	 * @param frequentSenior : userid에 해당하는 enter정보
	 * @param seniorList
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
