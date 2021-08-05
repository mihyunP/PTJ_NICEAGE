package com.ssafy.niceage.Domain.Enter;

import javax.persistence.*;

import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;

import lombok.Data;

@Entity
@Data
public class Enter {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "enter_id")
	private Long enterId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "senior_id")
    private Senior_Citizen_Center senior;
}
