package com.desafio2_zetta.todo_list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio2_zetta.todo_list.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId, org.springframework.data.domain.Sort sort);
}
