package com.ssafy.niceage.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "휴대폰 인증 정보")
public class AuthRequest {
	@ApiModelProperty(value="Id")
    private Long authId;

    @ApiModelProperty(value="Phone")
    private String phoneNumber;

    @ApiModelProperty(value="AuthNum")
    private String authNumber;

    @ApiModelProperty(value="Valid")
    private boolean authValid;
}
