package com.nyeras.student_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyeras.student_dashboard.dto.DashboardResponse;
import com.nyeras.student_dashboard.repository.AttendanceRepository;
import com.nyeras.student_dashboard.repository.StudentRepository;
import com.nyeras.student_dashboard.repository.TaskRepository;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;
    public String testMethod() {
    return "OK";
}

    public DashboardResponse getDashboardData() {

        long totalStudents = studentRepository.count();
        long totalTasks = taskRepository.count();
        long totalAttendance = attendanceRepository.count();

        long presentCount = attendanceRepository.countByStatus("Present");
        long absentCount = attendanceRepository.countByStatus("Absent");

        return new DashboardResponse(
                totalStudents,
                totalTasks,
                totalAttendance,
                presentCount,
                absentCount
        );
    }
}