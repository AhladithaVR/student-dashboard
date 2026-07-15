package com.nyeras.student_dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyeras.student_dashboard.entity.Attendance;
import com.nyeras.student_dashboard.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    // Add Attendance
    public Attendance addAttendance(Attendance attendance) {
        return repository.save(attendance);
    }

    // Get All Attendance
    public List<Attendance> getAllAttendance() {
        return repository.findAll();
    }

    // Get Attendance By ID
    public Attendance getAttendance(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update Attendance
    public Attendance updateAttendance(Long id, Attendance attendance) {

        Attendance existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setStudentId(attendance.getStudentId());
        existing.setStudentName(attendance.getStudentName());
        existing.setDate(attendance.getDate());
        existing.setStatus(attendance.getStatus());

        return repository.save(existing);
    }

    // Delete Attendance
    public void deleteAttendance(Long id) {
        repository.deleteById(id);
    }
}