package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Domain.User.UserDTO;
import com.ssafy.niceage.Service.DementiaService;
import com.ssafy.niceage.Service.Sos119Service;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sos119")
@RequiredArgsConstructor
public class Sos119Controller {
	private final Sos119Service sos119Service;
	private final UserService userService;

	@ApiOperation(value = "119 도움요청 클릭시", response = MainResponse.class)
	@GetMapping("/{userId}")
	public MainResponse dementiaResult(@ApiParam(value = "아이디") @PathVariable String userId) {

		MainResponse response = null;

		try {
			User user = userService.findById(userId);
			UserDTO userDto = new UserDTO(user);
			sos119Service.sendMessageTo119(userDto);
			response = new MainResponse("success", "119에 문자발송 성공");
		} catch (Exception e) {
			response = new MainResponse("게시글을 불러오지 못 했습니다", e.getMessage());
		}
		return response;
	}
}
