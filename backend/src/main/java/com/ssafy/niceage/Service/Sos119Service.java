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
public class Sos119Service {

	public void sendMessageTo119(UserDTO userDto) {
		String api_key = "NCSD8AKDVFYWT8DH";
		String api_secret = "GUS1EXEIK3Q24XAVVUJW2J6TEOCFGXHJ";

		Message Auth = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
		
//		params.put("to", "119");
		params.put("from", userDto.getUserPhone());
		params.put("type", "SMS");
		params.put("text", userDto.getUserName() + "님께서 위급상황이 발생했습니다. NiceAge 서비스에서 대신 도움요청 드립니다.");
		params.put("app_version", "test app 1.2");
		try {
			Auth.send(params);
		} catch (CoolsmsException e) {
			e.printStackTrace();
		}
		
	}
}
