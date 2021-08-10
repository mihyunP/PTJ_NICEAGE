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


}
