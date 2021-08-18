package com.ssafy.niceage.Domain.Senior_Citizen_Center;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Senior_Citizen_Center {


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "senior_id")
	private Long seniorId;

	@Column (name = "senior_name", nullable = false, length = 45)
    private String seniorName;
	
	@Column (name = "senior_address", nullable = false, length = 200)
    private String seniorAddress;

	@Builder
	public Senior_Citizen_Center(Long seniorId, String seniorName, String seniorAddress) {
		this.seniorId = seniorId;
		this.seniorName = seniorName;
		this.seniorAddress = seniorAddress;
	}

	
}
