package com.ssafy.niceage.Domain.Senior_Citizen_Center;

import org.junit.Assert;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "경로당DTO")
public class Senior_Citizen_CenterDTO {
	private Long seniorId;
	private String seniorName;
	private String seniorAddress;
	
	/**
	 * Assert.assertNotNull
	 * null값이 입력되면 안되는 컬럼들을 위한 안전장치
	 * @param seniorId
	 * @param seniorName
	 * @param seniorAddress
	 */
	@Builder
	public Senior_Citizen_CenterDTO(Long seniorId, String seniorName, String seniorAddress) {
		Assert.assertNotNull("seniorName must not be null", seniorName);
		Assert.assertNotNull("seniorAddress must not be null", seniorAddress);
		
		this.seniorId = seniorId;
		this.seniorName = seniorName;
		this.seniorAddress = seniorAddress;
	}
	
	@Builder
	public Senior_Citizen_CenterDTO(Senior_Citizen_Center senior) {
		Assert.assertNotNull("seniorName must not be null", senior.getSeniorName());
		Assert.assertNotNull("seniorAddress must not be null", senior.getSeniorAddress());
		
		this.seniorId = senior.getSeniorId();
		this.seniorName = senior.getSeniorName();
		this.seniorAddress = senior.getSeniorAddress();
	}
	
	public Senior_Citizen_Center toEntity() {
		return Senior_Citizen_Center.builder()
				.seniorId(this.seniorId)
				.seniorName(this.seniorName)
				.seniorAddress(this.seniorAddress)
				.build();
	}
	
}
