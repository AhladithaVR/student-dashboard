package com.nyeras.student_dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nyeras.student_dashboard.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    long countByStatus(String status);

}