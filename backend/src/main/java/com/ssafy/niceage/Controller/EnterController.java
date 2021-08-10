package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.SeniorRequest;
import com.ssafy.niceage.Domain.Enter.EnterDTO;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.EnterService;
import com.ssafy.niceage.Service.SeniorService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/enter")
@RequiredArgsConstructor
public class EnterController {

	private final SeniorService seniorService;
	private final UserService userService;
	private final EnterService enterService;
	
	@ApiOperation(value = "특정 경로당을 클릭시", response = MainResponse.class)
	@PostMapping("/enter")
	public MainResponse enterSenior(@RequestBody SeniorRequest request){
		MainResponse response = null;
		
		
		try {
			// request의 userid와 seniorid에 해당하는 user, senior entity를 가져와서 enter에 저장
			Long seniorId = request.getSeniorId();
			String userId = request.getUserId();
			Senior_Citizen_Center senior = seniorService.findBySeniorId(seniorId);
			User user = userService.findById(userId);
			EnterDTO enterDto = new EnterDTO(senior, user);
			enterService.save(enterDto);
			response = new MainResponse("success", "경로당 입장");
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		
		return response;
	}
}
