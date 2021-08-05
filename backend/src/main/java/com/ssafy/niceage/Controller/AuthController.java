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

import com.ssafy.niceage.Controller.Request.SmsRequest;
import com.ssafy.niceage.Controller.Request.SmsValidRequest;
import com.ssafy.niceage.Domain.Sms.Sms;
import com.ssafy.niceage.Service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api
@RestController
@RequestMapping("v1/auth")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

    @ApiOperation(value = "인증번호 요청", notes = "sms 인증번호 요청 클릭 시 인증번호 생성",response = BaseResponse.class)
    @PostMapping
    public BaseResponse postAuthNumber (@ApiParam(value = "휴대폰 번호") @RequestBody SmsValidRequest requset){
        BaseResponse response = null;
        try{
            Random random = new Random();
            String secret = "";
            for(int i= 0 ; i < 6; i++){
                secret += Integer.toString(random.nextInt(10));
            }
            List<Sms> findSms = authService.findByPhoneNumber(requset.getPhoneNumber()); // 핸드폰번호로 데이터 찾기
            if(findSms.size() > 0){ // 데이터가 있음
                authService.updateValid(requset); // 존재하는 번호 false 처리
            }
            // 새로운 인증번호 저장 + 유효하게 처리
            SmsRequest smsRequset = new SmsRequest();
            smsRequset.setPhoneNumber(requset.getPhoneNumber());
            smsRequset.setAuthNumber(secret);
            Sms sms = Sms.createSms(smsRequset);
            authService.save(sms);
            authService.sendSMS(requset.getPhoneNumber(),secret); // 문자발송
            response = new BaseResponse("success", "문자발송 성공");
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "인증번호 확인", notes = "인증 클릭 시",response = BaseResponse.class)
    @GetMapping("/{phoneNumber}/{smsCheckNum}")
    public BaseResponse GetAuthResult(
            @ApiParam(value = "휴대폰 번호") @PathVariable String phoneNumber,
            @ApiParam(value = "인증번호") @PathVariable String smsCheckNum){
        BaseResponse response = null;
        try{
            List<Sms> findSms = authService.findByPhoneNumber(phoneNumber); // 핸드폰번호로 데이터 찾기
            if(findSms.size() > 0){ // 데이터가 있음
                boolean isCheck = authService.checkNum(smsCheckNum);
                if(isCheck){
                    response = new BaseResponse("success", "true"); // 일치한 번호 있음
                }else{
                    response = new BaseResponse("success", "false"); // 일치한 번호 없음
                }
            }else{
                response = new BaseResponse("success", "데이터 없음");
            }
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
