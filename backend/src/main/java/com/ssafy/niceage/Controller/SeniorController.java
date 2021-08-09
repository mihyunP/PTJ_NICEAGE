package com.ssafy.niceage.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.SeniorRequest;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Service.SeniorService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/senior")
@RequiredArgsConstructor
public class SeniorController {
	private final SeniorService seniorService;
	private final UserService userService;
	
	@ApiOperation(value = "경로당 서비스 클릭시", response = MainResponse.class)
	@GetMapping("/show/{userid}")
	public MainResponse showSenior(@ApiParam(value = "아이디")@PathVariable String userId){
		MainResponse response = null;
		String userAddress = userService.findById(userId).getUserAddress();
		
		List<Senior_Citizen_Center> seniorList = seniorService.findByseniorAddress(userAddress);
		try {
			response = new MainResponse("success", seniorList);
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		
		return response;
	}
	
	
	
}
