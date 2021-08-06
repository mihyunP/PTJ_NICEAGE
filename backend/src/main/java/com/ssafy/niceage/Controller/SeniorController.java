package com.ssafy.niceage.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.niceage.Controller.Request.SeniorRequest;
import com.ssafy.niceage.Controller.Request.UserRequest;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.SeniorService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	@PostMapping("/")
	public MainResponse showSenior(@RequestBody SeniorRequest request){
		MainResponse response = null;
		String userId = request.getUserId();
		String userAddress = userService.findById(userId).getUserAddress();
		
		List<Senior_Citizen_Center> seniorList = seniorService.findByseniorAddress(userAddress);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seniorList);
			response = new MainResponse("success", jsonString);
		} catch (JsonProcessingException e) {
			response = new MainResponse("fail", e.getMessage());
		}
		
		return response;
	}
	
}
