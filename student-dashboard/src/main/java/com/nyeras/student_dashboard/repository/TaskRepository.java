package com.nyeras.student_dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nyeras.student_dashboard.entity.Task;
import com.nyeras.student_dashboard.entity.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);
    long countByStatus(String status);

}