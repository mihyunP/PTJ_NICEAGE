package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.MatchingRequest;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.MatchingService;
import com.ssafy.niceage.Service.UserService;

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
	private final UserService userService;
	
	@ApiOperation(value = "1:1친구매칭", response = MainResponse.class)
	@PostMapping("/match")
	public MainResponse matchFriend(@RequestBody MatchingRequest request) {

		MainResponse response = null;

		try {
			// 로그인 아이디로 회원객체 먼저 가져오기
			User user = userService.findById(request.getUserId());
			matchingService.addList(request);
			long roomNumber = matchingService.findList(request);
			matchingService.deleteList(request.getUserId());
			response = new MainResponse("success", roomNumber);
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}

		return response;

	}

	@ApiOperation(value = "1:1친구매칭 취소", response = MainResponse.class)
	@GetMapping("/cancel/{userId}")
	public MainResponse cancelMatch(@ApiParam(value = "유저 아이디") @PathVariable String userId) {
		
		MainResponse response = null;
		
		try {
			User user = userService.findById(userId);
			matchingService.cancel(user);
			response = new MainResponse("success", "매칭이 취소됐습니다.");
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		
		return response;
		
	}
}
