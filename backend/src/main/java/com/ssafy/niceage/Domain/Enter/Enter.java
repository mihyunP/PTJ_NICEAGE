package com.ssafy.niceage.Domain.Enter;

import javax.persistence.*;

import com.ssafy.niceage.Domain.Senior_Citizen_Center.Senior_Citizen_Center;
import com.ssafy.niceage.Domain.User.User;

import lombok.Data;

@Entity
@Data
public class Enter {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "enter_id")
	private int Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "user_no")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "senior_id")
    private Senior_Citizen_Center senior;

	public Enter(int id, User user, Senior_Citizen_Center senior) {
		super();
		Id = id;
		this.user = user;
		this.senior = senior;
	}
}
