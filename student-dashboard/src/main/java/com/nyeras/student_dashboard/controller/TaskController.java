package com.nyeras.student_dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyeras.student_dashboard.entity.Task;
import com.nyeras.student_dashboard.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
public Task saveTask(
        @RequestBody Task task,
        Authentication authentication
){
    return taskService.saveTask(task, authentication.getName());
}
    @GetMapping
public List<Task> getAllTasks(Authentication authentication) {
    return taskService.getAllTasks(authentication.getName());
}
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
   @PutMapping("/{id}")
public Task updateTask(
        @PathVariable Long id,
        @RequestBody Task task
){
    task.setId(id);
    return taskService.updateTask(task);
} 
}
