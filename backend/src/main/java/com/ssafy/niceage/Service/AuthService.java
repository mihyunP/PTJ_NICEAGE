package com.ssafy.niceage.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Controller.Request.AuthValidRequest;
import com.ssafy.niceage.Domain.Auth.Auth;
import com.ssafy.niceage.Repository.AuthRepository;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@RequiredArgsConstructor
public class AuthService {
	 private final AuthRepository authRepository;

	    /**
	     * 입력된 휴대폰 번호의 인증내역들 불러오기
	     * @param
	     */
	    @Transactional
	    public List<Auth> findByPhoneNumber(String phoneNumber) {
	        return authRepository.findByPhoneNumber(phoneNumber);
	    }

	    /**
	     * 인증 번호 사용불가로 바꾸기
	     */
	    @Transactional
	    public void updateValid(AuthValidRequest requset) {
	        Optional<List<Auth>> findAuth = Optional.ofNullable(authRepository.findByPhoneNumber(requset.getPhoneNumber()));
	        if(findAuth.isPresent()){
	            for (int i = 0; i < findAuth.get().size(); i++) {
	            	findAuth.get().get(i).setAuthValid(false);
	            }
	        }else{
	            throw new IllegalStateException("잘못된 핸드폰 번호입니다.");
	        }
	    }

	    /**
	     * 새 인증 번호 저장
	     */
	    @Transactional
	    public Auth save(Auth auth) {
	        return authRepository.save(auth);
	    }

	    /**
	     * 인증번호 일치 여부 확인
	     */
	    @Transactional
	    public boolean checkNum(String authNum) {
	    	Auth auth = authRepository.findByAuthNumber(authNum);
	        if (auth != null){ 
	            if(auth.isAuthValid()){
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
	    public void sendAuth(String phoneNumber, String cerNum) throws CoolsmsException {
	        String api_key = "NCSGPNSXP9HXOWQ4";
	        String api_secret = "LYET2SQAVHGBYAOXIXFFYR8WLW4ANBCG";

	        Message Auth = new Message(api_key, api_secret);
	        HashMap<String, String> params = new HashMap<String, String>();
	        params.put("to", phoneNumber);
	        params.put("from", "01063366282");
	        params.put("type", "SMS");
	        params.put("text", "NICEAGE 휴대폰 인증입니다.\n인증번호는 "+"["+ cerNum+"]" + "입니다.");
	        params.put("app_version", "test app 1.2");
	        Auth.send(params);
	    }
}
