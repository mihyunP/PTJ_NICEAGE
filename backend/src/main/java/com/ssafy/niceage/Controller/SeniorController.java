package com.ssafy.niceage.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Domain.Enter.Enter;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_CenterDTO;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.EnterService;
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
	private final EnterService enterService;
	
	@ApiOperation(value = "경로당 서비스 클릭시", response = MainResponse.class)
	@GetMapping("/show/{userId}")
	public MainResponse showSenior(@ApiParam(value = "아이디")@PathVariable String userId){
		MainResponse response = null;
		
		try {
			User user = userService.findById(userId);
			List<Senior_Citizen_Center> seniorList = seniorService.findBySeniorAddress(user.getUserAddress());
			response = new MainResponse("success", seniorList);
			System.out.println(response);
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		
		return response;
	}
	
	@ApiOperation(value = "자주가는 경로당 클릭시", response = MainResponse.class)
	@GetMapping("/frequence/{userId}")
	public MainResponse frequentSenior(@ApiParam(value = "아이디")@PathVariable String userId){
		MainResponse response = null;
		
		try {
			User user = userService.findById(userId);
			List<Enter> frequentSenior = enterService.findByUser(user);
			List<Senior_Citizen_Center> seniorList = seniorService.frequentSeniorList(frequentSenior);
			List<Senior_Citizen_CenterDTO> collect = seniorList.stream()
						.map(m-> new Senior_Citizen_CenterDTO(m))
						.collect(Collectors.toList());
			response = new MainResponse("success", collect);
			System.out.println(response);
		} catch (Exception e) {
			response = new MainResponse("fail", e.getMessage());
		}
		
		return response;
	}
}
