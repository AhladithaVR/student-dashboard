package com.nyeras.student_dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyeras.student_dashboard.entity.Task;
import com.nyeras.student_dashboard.entity.User;
import com.nyeras.student_dashboard.repository.TaskRepository;
import com.nyeras.student_dashboard.repository.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Save Task for Logged-in User
    public Task saveTask(Task task, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);

        return taskRepository.save(task);
    }

    // Get All Tasks
    public List<Task> getAllTasks(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return taskRepository.findByUser(user);
}

    // Get Task By ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Update Task
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    // Delete Task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}