package com.ssafy.niceage.Domain.Enter;

import javax.persistence.*;

import org.junit.Assert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Enter {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "enter_id")
	private Long enterId;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
	private User user;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "senior_id")
    private Senior_Citizen_Center senior;

	@Builder
	public Enter(Long enterId, User user, Senior_Citizen_Center senior) {
		Assert.assertNotNull("user must not be null", user);
		Assert.assertNotNull("senior must not be null", senior);
		
		this.enterId = enterId;
		this.user = user;
		this.senior = senior;
	}
	
}
