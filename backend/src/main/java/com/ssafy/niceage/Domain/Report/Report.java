package com.ssafy.niceage.Domain.Report;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Report {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "report_id")
	private int reportId;
	
	@Column (name = "reporter_id", nullable = false, length = 45)
	private String reporterId;
	
	@Column (name = "reporter_target_id", nullable = false, length = 45)
    private String reporterTargetId;
	
	@Column (name = "report_date")
    private String roportDate;
}
