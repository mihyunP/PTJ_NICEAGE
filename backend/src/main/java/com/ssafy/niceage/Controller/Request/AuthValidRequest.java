package com.ssafy.niceage.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "새 인증 요청 시 사용")
public class AuthValidRequest {
	 @ApiModelProperty(value="Phone")
	 private String phoneNumber;

	 @ApiModelProperty(value="Valid")
	 private boolean authValid;
}
