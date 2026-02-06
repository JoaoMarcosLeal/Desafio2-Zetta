package com.desafio2_zetta.todo_list.entity.tasks;

public record TaskResponseDTO(Long id, String nome, String descricao, boolean realizado, int prioridade) {
    public TaskResponseDTO(Task task) {
        this(task.getId(), task.getNome(), task.getDescricao(), task.isRealizada(), task.getPrioridade());
    }
}