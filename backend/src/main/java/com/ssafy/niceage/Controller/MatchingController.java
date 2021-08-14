package com.ssafy.niceage.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.MatchingRequest;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.MatchingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchingController {
	private final MatchingService matchingService;
	
	@ApiOperation(value = "1:1친구매칭", response = MainResponse.class)
	@PostMapping("/match")
	public MainResponse matchFriend(@RequestBody MatchingRequest request) {

		MainResponse response = null;

		try {
			// 로그인 아이디로 회원객체 먼저 가져오기
			matchingService.addList(request);
			long roomNumber = matchingService.findList(request);
			
			response = new MainResponse("success", roomNumber);
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}

		return response;

	}
	
	@ApiOperation(value = "1:1매칭이 성공했을때", response = MainResponse.class)
	@GetMapping("/success/{userId}")
	public MainResponse showSenior(@ApiParam(value = "아이디")@PathVariable String userId){
		MainResponse response = null;
		
		try {
			matchingService.deleteList(userId);
			response = new MainResponse("success", "매칭 정보 삭제");
			System.out.println(response);
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		return response;
	}
}
