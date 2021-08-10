package com.ssafy.niceage.Domain.Report;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.niceage.Domain.User.UserDTO;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "신고 Dto")
public class ReportDTO {
	private String reporterId;
    private String reporterTargetId;
    private String roportDate;
    
	public ReportDTO(Report report) {
		super();
		this.reporterId = report.getReporterId();
		this.reporterTargetId = report.getReporterTargetId();
		this.roportDate = report.getRoportDate();
	}
    
    
}
