package com.nyeras.student_dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nyeras.student_dashboard.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}