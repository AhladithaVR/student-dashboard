package com.nyeras.student_dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyeras.student_dashboard.entity.Attendance;
import com.nyeras.student_dashboard.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // Add Attendance
    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return service.addAttendance(attendance);
    }

    // Get All Attendance
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return service.getAllAttendance();
    }

    // Get Attendance By ID
    @GetMapping("/{id}")
    public Attendance getAttendance(@PathVariable Long id) {
        return service.getAttendance(id);
    }

    // Update Attendance
    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Long id,
                                       @RequestBody Attendance attendance) {
        return service.updateAttendance(id, attendance);
    }

    // Delete Attendance
    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        service.deleteAttendance(id);
    }
}