package com.ssafy.niceage.Domain.Enter;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class EnterDTO {
	@Id @GeneratedValue
	@Column (name = "enter_id")
	private int Id;
	
	@Column (name = "user_id")
	private String UserId;
	
	@Column (name = "senior_id")
    private String SeniorId;
	
	public EnterDTO(int id, String userId, String seniorId) {
		super();
		Id = id;
		UserId = userId;
		SeniorId = seniorId;
	}
}
