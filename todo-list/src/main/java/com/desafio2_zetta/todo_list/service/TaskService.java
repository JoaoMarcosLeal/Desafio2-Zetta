package com.desafio2_zetta.todo_list.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafio2_zetta.todo_list.entity.Task;
import com.desafio2_zetta.todo_list.repository.TaskRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> create(Task task) {
        taskRepository.save(task);
        return list();
    }

    public List<Task> list() {
        Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());
        return taskRepository.findAll(sort);
    }

    public List<Task> update(Task task) {
        taskRepository.save(task);
        return list();
    }

    public List<Task> delete(Long id) {
        taskRepository.deleteById(id);
        return list();
    }
}