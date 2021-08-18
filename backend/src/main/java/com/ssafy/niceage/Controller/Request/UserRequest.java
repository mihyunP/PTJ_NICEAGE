package com.ssafy.niceage.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "회원 정보")
public class UserRequest {
	@ApiModelProperty(value = "Id")
	private String userId;
	
	@ApiModelProperty(value = "Password")
	private String userPassword;

	@ApiModelProperty(value = "Name")
	private String userName;

	@ApiModelProperty(value = "Address")
	private String userAddress;
	
	@ApiModelProperty(value = "Gender")
	private String userGender;
	
	@ApiModelProperty(value = "Birth")
	private String userBirth;
	
	@ApiModelProperty(value = "Phone")
	private String userPhone;
	
	@ApiModelProperty(value = "Emergency")
	private String userEmergency;
	
}
