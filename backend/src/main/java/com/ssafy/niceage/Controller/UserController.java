package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.UserRequest;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/user")
public class UserController {
	private final UserService userService;

	@ApiOperation(value = "회원가입", notes = "가입 성공시 ", response = BaseResponse.class)
	@PostMapping("/join")
	public BaseResponse join(@ApiParam(value = "사용자 객체") @RequestBody UserRequest request) {
		BaseResponse response = null;
		try {
			User user = User.createUser(request);
		}
	}

}
