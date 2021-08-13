package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.MatchingRequest;
import com.ssafy.niceage.Service.MatchingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}
