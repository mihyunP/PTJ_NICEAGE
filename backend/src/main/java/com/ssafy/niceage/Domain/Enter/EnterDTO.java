package com.ssafy.niceage.Domain.Enter;

import org.junit.Assert;

import com.ssafy.niceage.Controller.Request.SeniorRequest;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;

import lombok.Builder;

public class EnterDTO {
	private Long enterId;
	private User user;
	private Senior_Citizen_Center senior;
	
	@Builder
	public EnterDTO(Senior_Citizen_Center senior, User user) {
		Assert.assertNotNull("user must not be null", user);
		Assert.assertNotNull("senior must not be null", senior);
		
		this.user = user;
		this.senior = senior;
	}
	
	public Enter toEntity() {
		return Enter.builder()
				.enterId(this.enterId)
				.user(this.user)
				.senior(this.senior)
				.build();
	}
}
