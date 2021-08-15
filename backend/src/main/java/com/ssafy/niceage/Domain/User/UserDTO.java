package com.ssafy.niceage.Domain.User;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "회원 Dto")
public class UserDTO {
	private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userGender;
    private String userBirth;
    private String userPhone;
    private String userEmergency;
    
	public UserDTO(User user) {
		this.userId = user.getUserId();
		this.userPassword = user.getUserPassword();
		this.userName = user.getUserName();
		this.userAddress = user.getUserAddress();
		this.userGender = user.getUserGender();
		this.userBirth = user.getUserBirth();
		this.userPhone = user.getUserPhone();
		this.userEmergency = user.getUserEmergency();
	}
    
    
}
