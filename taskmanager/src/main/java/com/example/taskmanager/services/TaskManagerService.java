package com.example.taskmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskManagerService {
	
	@Autowired
	private TaskRepository taskrepo;
	
	//Function to Create Task and returning it back
	public Task createTask(Task t) {
		return taskrepo.save(t!=null? t:null);
	}
	
	//Function to Read a single task
	public Optional<Task> readTaskById(Long id) {
		return taskrepo.findById(id);
	}
	
	//Function to Read a all task
	public List<Task> readAllTask() {
		return taskrepo.findAll();
	}
	
	//Function to Update the task
	public Task updateTask(Long id,Task t) {
		 Task task = taskrepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));
		 task.setTitle(t.getTitle());
		 task.setDescription(t.getDescription());
		 task.setStatus(t.getStatus());
		 task.setStartDate(t.getStartDate());
		 task.setDueDate(t.getDueDate());
		 return taskrepo.save(task);
	}
	
	//Delete the task for given  Id
	public void deleteTask(Long id) {
		Task t = taskrepo.findById(id).orElseThrow(() -> new RuntimeException("Data not found! "));
		taskrepo.delete(t);
	}
	

}
