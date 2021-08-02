package com.ssafy.niceage.Domain.Report;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class ReportDTO {
	@Id @GeneratedValue
	@Column (name = "report_id")
	private int Id;
	
	@Column (name = "reporter_id")
	private String ReporterId;
	
	@Column (name = "reporter_target_id")
    private String ReporterTargetId;
	
	@Column (name = "report_date")
    private String date;

	public ReportDTO(int id, String reporterId, String reporterTargetId, String date) {
		super();
		Id = id;
		ReporterId = reporterId;
		ReporterTargetId = reporterTargetId;
		this.date = date;
	}
}
