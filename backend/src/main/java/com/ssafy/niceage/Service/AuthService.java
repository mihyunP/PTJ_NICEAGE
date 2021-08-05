package com.ssafy.niceage.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Controller.Request.SmsValidRequest;
import com.ssafy.niceage.Domain.Sms.Sms;
import com.ssafy.niceage.Repository.SmsRepository;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@RequiredArgsConstructor
public class AuthService {
	 private final SmsRepository smsRepository;

	    /**
	     * 입력된 핸드폰 번호로 인증번호 리스트 불러오기
	     * @param phoneNumber
	     */
	    @Transactional
	    public List<Sms> findByPhoneNumber(String phoneNumber) {
	        return smsRepository.findByPhoneNumber(phoneNumber);
	    }

	    /**
	     * 상태 false로 바꾸기
	     */
	    @Transactional
	    public void updateValid(SmsValidRequest requset) {
	        Optional<List<Sms>> findSms = Optional.ofNullable(smsRepository.findByPhoneNumber(requset.getPhoneNumber()));
	        if(findSms.isPresent()){
	            for (int i = 0; i < findSms.get().size(); i++) {
	                findSms.get().get(i).setValid(false);
	            }
	        }else{
	            throw new IllegalStateException("잘못된 핸드폰 번호입니다.");
	        }
	    }

	    /**
	     * 새로운 인증번호 저장 + true 처리
	     */
	    @Transactional
	    public Sms save(Sms sms) {
	        return smsRepository.save(sms);
	    }

	    /**
	     * 입력 인증번호와 테이블의 true 인증번호가 같은지
	     * => 같으면 true , 다르면 false
	     */
	    @Transactional
	    public boolean checkNum(String smsCheckNum) {
	        Sms sms = smsRepository.findByAuthNumber(smsCheckNum);
	        if (sms != null){ // 동일한 인증번호 존재
	            if(sms.isValid()){
	                return true;
	            }else {
	                return false;
	            }
	        }else {
	            return false;
	        }
	    }

	    /**
	     * 문자보내기
	     */
	    @Transactional
	    public void sendSMS(String phoneNumber, String secret) throws CoolsmsException {
	        String api_key = "NCSVY6YWZAD13YOZ";
	        String api_secret = "IJOI5OIVVRY9JXJFBI3R4HUA7WSOLUWK";

	        Message SMS = new Message(api_key, api_secret);
	        HashMap<String, String> params = new HashMap<String, String>();
	        params.put("to", phoneNumber);
	        params.put("from", "01091396956"); //무조건 자기번호 (인증)
	        params.put("type", "SMS");
	        params.put("text", "NICEAGE 휴대폰 인증입니다.\n인증번호는 "+"["+ secret+"]" + "입니다.");
	        params.put("app_version", "test app 1.2"); // application name and version
	        SMS.send(params);
	    }
}
