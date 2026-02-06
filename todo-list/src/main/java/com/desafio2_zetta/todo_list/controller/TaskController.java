package com.desafio2_zetta.todo_list.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio2_zetta.todo_list.entity.tasks.Task;
import com.desafio2_zetta.todo_list.entity.tasks.TaskResponseDTO;
import com.desafio2_zetta.todo_list.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public List<TaskResponseDTO> create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping
    public List<TaskResponseDTO> update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @GetMapping
    public List<TaskResponseDTO> list() {
        return taskService.list();
    }

    @DeleteMapping("{id}")
    public List<TaskResponseDTO> delete(@PathVariable("id") Long id) {
        return taskService.delete(id);

    }

}
