package com.ssafy.niceage.Domain.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.niceage.Controller.Request.UserRequest;
import com.ssafy.niceage.Domain.Enter.Enter;

import lombok.Data;

@Entity
@Data
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "user_no", nullable = false)
	private Long userNo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval =true)
	List<Enter> enters = new ArrayList<Enter>();
	
	@Column (name = "user_id", nullable = false, length = 45)
	private String userId;
	
	@Column (name = "user_password", nullable = false, length = 45)
    private String userPassword;
	
	@Column (name = "user_name", nullable = false, length = 45)
    private String userName;
	
	@Column (name = "user_address", nullable = false, length = 200)
    private String userAddress;
	
	@Column (name = "user_gender", nullable = false, length = 1)
    private String userGender;
	
	@Column (name = "user_birth", nullable = false)
    private String userBirth;
	
	@Column (name = "user_phone_number", nullable = false, length = 30)
    private String userPhone;
	
	@Column (name = "user_emergency_number", nullable = true, length = 30)
    private String userEmergency;
	
	public static User createUser(UserRequest request) {
		User userInput = new User();
		userInput.userId = request.getUserId();
        userInput.setUserId(request.getUserId());
        userInput.setUserPassword(request.getUserPassword());
        userInput.setUserName(request.getUserName());
        userInput.setUserAddress(request.getUserAddress());
        userInput.setUserGender(request.getUserGender());
        userInput.setUserBirth(request.getUserBirth());
        userInput.setUserPhone(request.getUserPhone());
        userInput.setUserEmergency(request.getUserEmergency());
        return userInput;
	}
}
