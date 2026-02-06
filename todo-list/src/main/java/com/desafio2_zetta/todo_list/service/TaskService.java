package com.desafio2_zetta.todo_list.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.desafio2_zetta.todo_list.entity.tasks.Task;
import com.desafio2_zetta.todo_list.entity.tasks.TaskResponseDTO;
import com.desafio2_zetta.todo_list.entity.users.User;
import com.desafio2_zetta.todo_list.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Método auxiliar para pegar o usuário logado de forma 'limpa'
    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public List<TaskResponseDTO> create(Task task) {
        // Vincula a tarefa ao usuário logado antes de salvar
        task.setUser(getAuthenticatedUser());
        taskRepository.save(task);
        return list();
    }

    public List<TaskResponseDTO> list() {
        User user = getAuthenticatedUser();
        Sort sort = Sort.by("prioridade").descending().and(Sort.by("nome").ascending());

        return taskRepository.findByUserId(user.getId(), sort)
                .stream()
                .map(TaskResponseDTO::new)
                .toList();
    }

    public List<TaskResponseDTO> update(Task task) {
        // Antes de atualizar, valida se a tarefa pertence mesmo ao usuário
        Task existingTask = taskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (!existingTask.getUser().getId().equals(getAuthenticatedUser().getId())) {
            throw new RuntimeException("Você não tem permissão para alterar esta tarefa");
        }

        task.setUser(getAuthenticatedUser());
        taskRepository.save(task);
        return list();
    }

    public List<TaskResponseDTO> delete(Long id) {
        // Validação de segurança para impedir que deletem tarefas de outros
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (!existingTask.getUser().getId().equals(getAuthenticatedUser().getId())) {
            throw new RuntimeException("Você não tem permissão para deletar esta tarefa");
        }

        taskRepository.deleteById(id);
        return list();
    }
}