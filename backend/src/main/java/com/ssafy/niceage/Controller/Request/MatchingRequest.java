package com.ssafy.niceage.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "클라이언트에서 1:1친구매칭을 했을때 오는 정보", description = "게시판 CRUD에 필요한 회원ID와 제목, 내용")
public class MatchingRequest {
	@ApiModelProperty(value = "userId")
	private String userId;
	
	@ApiModelProperty(value = "firstChoice")
	private int firstChoice;
	
	@ApiModelProperty(value = "secondChoice")
	private int secondChoice;

	@ApiModelProperty(value = "thirdChoice")
	private int thirdChoice;
}
