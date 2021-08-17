package com.ssafy.niceage.Domain.Report;

import java.time.LocalDateTime;

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
    private LocalDateTime reportDate;
    
	public ReportDTO(Report report) {
		super();
		this.reporterId = report.getReporterId();
		this.reporterTargetId = report.getReporterTargetId();
		this.reportDate = report.getReportDate();
	}
    
    
}
