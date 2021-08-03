package com.ssafy.niceage.Domain.Report;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Report {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "report_id")
	private int Id;
	
	@Column (name = "reporter_id", nullable = false, length = 45)
	private String ReporterId;
	
	@Column (name = "reporter_target_id", nullable = false, length = 45)
    private String ReporterTargetId;
	
	@Column (name = "report_date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date Date;

	public Report(int id, String reporterId, String reporterTargetId, java.util.Date date) {
		super();
		Id = id;
		ReporterId = reporterId;
		ReporterTargetId = reporterTargetId;
		Date = date;
	}
}
