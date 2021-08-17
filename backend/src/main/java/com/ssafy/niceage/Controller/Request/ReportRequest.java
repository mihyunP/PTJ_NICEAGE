package com.ssafy.niceage.Controller.Request;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "신고 정보")
public class ReportRequest {
	@ApiModelProperty(value = "reporterId")
	private String reporterId;
	
	@ApiModelProperty(value = "reporterTargetId")
    private String reporterTargetId;
	
	@ApiModelProperty(value = "roportDate")
    private LocalDateTime reportDate;
}
