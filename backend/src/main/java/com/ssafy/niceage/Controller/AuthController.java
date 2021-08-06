package com.ssafy.niceage.Controller;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.niceage.Controller.Request.AuthRequest;
import com.ssafy.niceage.Controller.Request.AuthValidRequest;
import com.ssafy.niceage.Domain.Auth.Auth;
import com.ssafy.niceage.Service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

    @ApiOperation(value = "휴대폰 인증번호 요청", notes = "인증 버튼 클릭 시 인증번호 전송",response = MainResponse.class)
    @PostMapping
    public MainResponse postAuthNumber (@ApiParam(value = "휴대폰 번호") @RequestBody AuthValidRequest requset){
        MainResponse response = null;
        try{
            Random random = new Random();
            String cerNum = "";
            for(int i= 0 ; i < 6; i++){
            	cerNum += Integer.toString(random.nextInt(10));
            }
            List<Auth> findAuth = authService.findByPhoneNumber(requset.getPhoneNumber());
            if(findAuth.size() > 0){ 
                authService.updateValid(requset);
            }
            
            AuthRequest authRequset = new AuthRequest();
            authRequset.setPhoneNumber(requset.getPhoneNumber());
            authRequset.setAuthNumber(cerNum);
            Auth auth = Auth.createAuth(authRequset);
            authService.save(auth);
            authService.sendAuth(requset.getPhoneNumber(),cerNum); 
            response = new MainResponse("success", "문자발송 성공");
        }catch(Exception e){
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "인증번호 일치 확인", notes = "회원가입 완료 클릭 시",response = MainResponse.class)
    @GetMapping("/{phoneNumber}/{authNum}")
    public MainResponse GetAuthResult(
            @ApiParam(value = "휴대폰 번호") @PathVariable String phoneNumber,
            @ApiParam(value = "인증 번호") @PathVariable String authNum){
        MainResponse response = null;
        try{
            List<Auth> findAuth = authService.findByPhoneNumber(phoneNumber);
            if(findAuth.size() > 0){
                boolean isCheck = authService.checkNum(authNum);
                if(isCheck){
                    response = new MainResponse("success", "true");
                }else{
                    response = new MainResponse("success", "false");
                }
            }else{
                response = new MainResponse("success", "데이터 없음");
            }
        }catch(Exception e){
            response = new MainResponse("fail", e.getMessage());
        }
        return response;
    }
}
