package com.amatsolutions.samples.taskmanager.controller;


import com.amatsolutions.samples.taskmanager.model.Task;
import com.amatsolutions.samples.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<Task> getTasksForMonth(@RequestParam int year, @RequestParam int month) {
        return taskService.getTasksForMonth(year, month);
    }
}
