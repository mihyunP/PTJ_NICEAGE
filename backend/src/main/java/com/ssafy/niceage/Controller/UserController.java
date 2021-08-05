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
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@ApiOperation(value = "회원가입", notes = "가입 성공시 BaseResponse에 data값으로 '성공적으로 가입' 설정 후 반환", response = BaseResponse.class)
    @PostMapping("/join")
    public BaseResponse join(@ApiParam(value = "사용자 객체") @RequestBody UserRequest request){

        BaseResponse response = null;
        try{
            User user = User.createUser(request);

            userService.save(user);
            response = new BaseResponse("success", "성공적으로 가입");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }
	
	@ApiOperation(value = "아이디 중복여부 검사", notes = "반환되는 data 값은 true / false", response = BaseResponse.class)
    @GetMapping("/check/{userId}")
    public BaseResponse duplicateUserLoginId(@ApiParam(value = "중복검사 할 아이디")@PathVariable String userId){
        BaseResponse response = null;
        try{
            boolean isCheck = userService.checkUserId(userId);
            if(isCheck){
                response = new BaseResponse("success", true);
            }else{
                response = new BaseResponse("success", false);
            }
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
	

    @ApiOperation(value = "핸드폰 번호에 해당하는 유저 찾기(인증번호 전송 시)", notes = "찾으면 success, 못찾으면 fail", response = BaseResponse.class)
    @GetMapping("/phone/{userPhone}")
    public BaseResponse findUserByPhone(@ApiParam(value = "사용자 핸드폰 번호") @PathVariable String userPhone){
        BaseResponse response = null;
        try{
            User findUser = userService.findByUserPhone(userPhone);
            if(findUser == null){
                response = new BaseResponse("fail",null);
            }else{
                response = new BaseResponse("success", findUser);
            }
        }
        catch(Exception e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }
}
