package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Domain.User.UserDTO;
import com.ssafy.niceage.Service.DementiaService;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dementia")
@RequiredArgsConstructor
public class DementiaController {
	private final DementiaService dementiaService;
	private final UserService userService;
	
	@ApiOperation(value = "치매진단결과 제출시", response = MainResponse.class)
	@GetMapping("/dementia/{userId}/{result}")
	public MainResponse dementiaResult(@ApiParam(value = "아이디")@PathVariable String userId, Integer result) {

		MainResponse response = null;

		try {
			User user = userService.findById(userId);
			UserDTO userDto = new UserDTO(user);
			boolean sendMessage = dementiaService.checkDementia(userDto, result);
			if (sendMessage) {
				response = new MainResponse("success", "치매판정을 받았습니다. 보호자 혹은 본인 핸드폰으로 문자를 확인해 주세요.");				
			} else {
				response = new MainResponse("success", "치매가 아닙니다.");				
			}
		} catch (Exception e) {
			response = new MainResponse("게시글을 불러오지 못 했습니다", e.getMessage());
		}
		return response;
	}
}
