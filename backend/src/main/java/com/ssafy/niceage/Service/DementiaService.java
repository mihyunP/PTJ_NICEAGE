package com.ssafy.niceage.Service;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.ssafy.niceage.Domain.User.UserDTO;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@RequiredArgsConstructor
@Transactional
public class DementiaService {

	/**
	 * 치매 진단결과 문자보내기
	 */
	public boolean checkDementia(UserDTO userDto, int result) {
		if (result >= 10) {
			String api_key = "NCSGPNSXP9HXOWQ4";
			String api_secret = "LYET2SQAVHGBYAOXIXFFYR8WLW4ANBCG";

			Message Auth = new Message(api_key, api_secret);
			HashMap<String, String> params = new HashMap<String, String>();
			// 보호자 번호가 있다면 그 번호로, 없다면 본인 번호로 문자 발송
			if (userDto.getUserEmergency().isEmpty()) {
				params.put("to", userDto.getUserPhone());
			} else {
				params.put("to", userDto.getUserEmergency());
			}
			params.put("from", "01063366282");
			params.put("type", "SMS");
			params.put("text", userDto.getUserName() + "님은 치매 초기 증상으로 보입니다.");
			params.put("app_version", "test app 1.2");
			try {
				Auth.send(params);
			} catch (CoolsmsException e) {
				e.printStackTrace();
			}
			
			return true;
		} else {
			return false;
		}
		
	}
}
