package com.ssafy.niceage.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "클라이언트에서 회원, 경로당 ID를 담은 객체", description = "경로당 클릭(입장)시 요청")
public class EnterRequest {
	@ApiModelProperty(value = "UserID")
	private String userId;
	
	@ApiModelProperty(value = "SeniorID")
	private Long seniorId;
}
