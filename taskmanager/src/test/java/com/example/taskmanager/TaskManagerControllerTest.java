package com.example.taskmanager;

import static org.mockito.Mockito.when;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.taskmanager.model.*;
import com.example.taskmanager.services.*;
import org.junit.jupiter.api.Test;
import com.example.taskmanager.controller.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskManagerController.class)
public class TaskManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskManagerService taskService;

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setup() {
    	task1 = new Task();
    	task1.setId(1L);
    	task1.setTitle("Task 1");
    	task1.setDescription("Description for Task 1");
    	task1.setStatus("Pending");
    	task1.setStartDate(LocalDate.now());
    	task1.setDueDate(LocalDate.now().plusDays(1));
    	
    	task2 = new Task();
    	task2.setId(2L);
    	task2.setTitle("Task 2");
    	task2.setDescription("Description for Task 2");
    	task2.setStatus("Pending");
    	task2.setStartDate(LocalDate.now());
    	task2.setDueDate(LocalDate.now().plusDays(1));
    	
    }
    
    @Test
    public void testGetAllTasks() throws Exception{
    	when(taskService.readAllTask()).thenReturn(Arrays.asList(task1,task2));
    	
    	 mockMvc.perform(get("/api/taskmanager/tasks"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].id").value(task1.getId()))
         .andExpect(jsonPath("$[0].title").value(task1.getTitle()))
         .andExpect(jsonPath("$[0].description").value(task1.getDescription()))
         .andExpect(jsonPath("$[0].status").value(task1.getStatus()))
         .andExpect(jsonPath("$[0].dueDate").value(task1.getDueDate().toString()))
         .andExpect(jsonPath("$[0].startDate").value(task1.getStartDate().toString()))
         .andExpect(jsonPath("$[1].id").value(task2.getId()))
         .andExpect(jsonPath("$[1].title").value(task2.getTitle()))
         .andExpect(jsonPath("$[1].description").value(task2.getDescription()))
         .andExpect(jsonPath("$[1].status").value(task2.getStatus()))
         .andExpect(jsonPath("$[1].dueDate").value(task2.getDueDate().toString()))
    	 .andExpect(jsonPath("$[1].startDate").value(task2.getStartDate().toString()));
    }
    
    @Test
    public void testGetTaskById() throws Exception {
        when(taskService.readTaskById(anyLong())).thenReturn(Optional.of(task1));

        mockMvc.perform(get("/api/taskmanager/tasks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task1.getId()))
                .andExpect(jsonPath("$.title").value(task1.getTitle()))
                .andExpect(jsonPath("$.description").value(task1.getDescription()))
                .andExpect(jsonPath("$.status").value(task1.getStatus()))
                .andExpect(jsonPath("$.startDate").value(task1.getStartDate().toString()))
                .andExpect(jsonPath("$.dueDate").value(task1.getDueDate().toString()));
    }
    
    @Test
    public void testCreateTask() throws Exception {
        when(taskService.createTask(any(Task.class))).thenReturn(task1);

        mockMvc.perform(post("/api/taskmanager/createtask")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Task 1\", \"description\": \"Description 1\", \"status\": \"Pending\", \"startDate\": \"" + LocalDate.now().toString() + "\", \"dueDate\": \"" + LocalDate.now().plusDays(7).toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task1.getId()))
                .andExpect(jsonPath("$.title").value(task1.getTitle()))
                .andExpect(jsonPath("$.description").value(task1.getDescription()))
                .andExpect(jsonPath("$.status").value(task1.getStatus()))
                .andExpect(jsonPath("$.startDate").value(task1.getStartDate().toString()))
                .andExpect(jsonPath("$.dueDate").value(task1.getDueDate().toString()));
    }

    @Test
    public void testUpdateTask() throws Exception {
        when(taskService.updateTask(anyLong(), any(Task.class))).thenReturn(task1);

        mockMvc.perform(put("/api/taskmanager/updatetask/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Updated Task\", \"description\": \"Updated Description\", \"status\": \"Completed\", \"startDate\": \"" + LocalDate.now().toString() + "\", \"dueDate\": \"" + LocalDate.now().plusDays(7).toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task1.getId()))
                .andExpect(jsonPath("$.title").value(task1.getTitle()))
                .andExpect(jsonPath("$.description").value(task1.getDescription()))
                .andExpect(jsonPath("$.status").value(task1.getStatus()))
                .andExpect(jsonPath("$.startDate").value(task1.getStartDate().toString()))
                .andExpect(jsonPath("$.dueDate").value(task1.getDueDate().toString()));
    }

    @Test
    public void testDeleteTask() throws Exception {
    	 mockMvc.perform(delete("/api/taskmanager/deletetask/{id}", 1L)
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(content().string("Task Deleted Successfully"));
    }
    
    
    
    
}
