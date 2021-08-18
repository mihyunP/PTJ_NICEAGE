package com.ssafy.niceage.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.niceage.Domain.Report.Report;
import com.ssafy.niceage.Repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
	private final ReportRepository reportRepository;
	
	/**
     * 신고 접수
     */
    @Transactional
    public Report save(Report report) {
        return reportRepository.save(report);
    }
    
    /**
     * 신고 목혹 전체 리스트 반환
     */
    @Transactional
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}
