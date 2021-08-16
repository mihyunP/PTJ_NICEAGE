package com.ssafy.niceage.Domain.Report;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.niceage.Controller.Request.ReportRequest;

import lombok.Data;

@Entity
@Data
public class Report {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "report_id", nullable = false)
	private Long reportId;
	
	@Column (name = "reporter_id", nullable = false, length = 45)
	private String reporterId;
	
	@Column (name = "reporter_target_id", nullable = false, length = 45)
    private String reporterTargetId;
	
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	@Column (name = "report_date", updatable=false)
    private LocalDateTime reportDate;
	
	public static Report receiptReport(ReportRequest request) {
		Report reportInput = new Report();
		reportInput.reporterId = request.getReporterId();
		reportInput.setReporterId(request.getReporterId());
		reportInput.setReporterTargetId(request.getReporterTargetId());
		reportInput.setReportDate(request.getReportDate());
        return reportInput;
	}

}
