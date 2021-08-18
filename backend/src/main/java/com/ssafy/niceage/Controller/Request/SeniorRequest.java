package com.ssafy.niceage.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Getter
@ApiModel(value = "클라이언트에서 회원 ID를 담은 객체정보", description = "경로당 서비스 이용시 요청이 들어옴")
public class SeniorRequest {
	@ApiModelProperty(value = "UserID")
	private String userId;
	
	@ApiModelProperty(value = "SeniorID")
	private Long seniorId;
}
