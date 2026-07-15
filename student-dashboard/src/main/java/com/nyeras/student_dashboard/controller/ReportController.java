package com.nyeras.student_dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyeras.student_dashboard.dto.ReportResponse;
import com.nyeras.student_dashboard.service.ReportService;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/api/reports")
    public ReportResponse getReport() {
        return reportService.getReport();
    }
}