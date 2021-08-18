package com.ssafy.niceage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.niceage.Domain.Report.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{

}
