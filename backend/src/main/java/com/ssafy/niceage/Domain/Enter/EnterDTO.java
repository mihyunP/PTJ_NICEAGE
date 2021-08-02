package com.ssafy.niceage.Domain.Enter;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class EnterDTO {
	@Id @GeneratedValue
	@Column (name = "enter_id")
	private int Id;
	
	@Column (name = "user_no")
	private String UserNo;
	
	@Column (name = "senior_id")
    private int SeniorId;

	public EnterDTO(int id, String userNo, int seniorId) {
		super();
		Id = id;
		UserNo = userNo;
		SeniorId = seniorId;
	}
}
