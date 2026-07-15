package com.nyeras.student_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyeras.student_dashboard.dto.ReportResponse;
import com.nyeras.student_dashboard.repository.AttendanceRepository;
import com.nyeras.student_dashboard.repository.StudentRepository;
import com.nyeras.student_dashboard.repository.TaskRepository;

@Service
public class ReportService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public ReportResponse getReport() {

        return new ReportResponse(
                studentRepository.count(),
                taskRepository.count(),
                taskRepository.countByStatus("Completed"),
                taskRepository.countByStatus("Pending"),
                attendanceRepository.count()
        );
    }
}
