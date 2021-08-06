package com.ssafy.niceage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@ApiOperation(value = "회원가입", response = MainResponse.class)
    @PostMapping("/join")
    public MainResponse join(@ApiParam(value = "회원 정보") @RequestBody UserRequest request){

        MainResponse response = null;
        try{
            User user = User.createUser(request);

            userService.save(user);
            response = new MainResponse("success", "가입 완료");
        }catch(IllegalStateException e){
            response = new MainResponse("fail",e.getMessage());
        }
        return response;
    }
	
	@ApiOperation(value = "아이디 중복 검사", response = MainResponse.class)
    @GetMapping("/check/{userId}")
    public MainResponse duplicateUserLoginId(@ApiParam(value = "아이디")@PathVariable String userId){
        MainResponse response = null;
        try{
            boolean isCheck = userService.checkUserId(userId);
            if(isCheck){
                response = new MainResponse("success", true);
            }else{
                response = new MainResponse("success", false);
            }
        }catch(Exception e){
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
	

    @ApiOperation(value = "휴대폰 번호에 해당하는 회원 찾기", response = MainResponse.class)
    @GetMapping("/phone/{userPhone}")
    public MainResponse findUserByPhone(@ApiParam(value = "휴대폰 번호") @PathVariable String userPhone){
        MainResponse response = null;
        try{
            User findUser = userService.findByUserPhone(userPhone);
            if(findUser == null){
                response = new MainResponse("fail",null);
            }else{
                response = new MainResponse("success", findUser);
            }
        }
        catch(Exception e){
            response = new MainResponse("fail",e.getMessage());
        }
        return response;
    }
}
