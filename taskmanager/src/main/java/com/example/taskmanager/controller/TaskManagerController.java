package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.services.TaskManagerService;

@RestController
@RequestMapping("/api/taskmanager")
public class TaskManagerController {
    
    @Autowired
    private TaskManagerService taskService;
    
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.readAllTask();
    }
    
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.readTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return ResponseEntity.ok(task);
    }
    
    @PostMapping("/createtask")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
    
    @PutMapping("/updatetask/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task t) {
        Task updatedTask = taskService.updateTask(id, t);
        return ResponseEntity.ok(updatedTask);
    }
    
    @DeleteMapping("/deletetask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task Deleted Successfully");
    }
}
