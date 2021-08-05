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
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Service.SeniorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/senior")
@RequiredArgsConstructor
public class SeniorController {
	private final SeniorService seniorService;
	
	@ApiOperation(value = "경로당 서비스 클릭시", response = BaseResponse.class)
	@PostMapping("/")
	
	public BaseResponse showSenior(@RequestBody Senior_Citizen_Center senior){
		BaseResponse response = null;
		Senior_Citizen_Center seniorShow = seniorService.findByseniorId(30);
//		List<Senior_Citizen_Center> seniorList = SeniorService.findAll();
		System.out.println("여기가 잘못");
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seniorShow);
			response = new BaseResponse("success", jsonString);
		} catch (JsonProcessingException e) {
			response = new BaseResponse("fail", e.getMessage());
		}
		
		return response;
	}
	
}
