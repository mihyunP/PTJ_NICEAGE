package com.ssafy.niceage.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.ssafy.niceage.Domain.User.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DementiaService {

	public String checkDementia(UserDTO userDto, int result) {
		if (result > 10) {
			if (userDto.getUserEmergency().isEmpty()) {
				return userDto.getUserPhone();
			} else {
				return userDto.getUserEmergency();
			}
		} else {
			return null;
		}
	}

}
