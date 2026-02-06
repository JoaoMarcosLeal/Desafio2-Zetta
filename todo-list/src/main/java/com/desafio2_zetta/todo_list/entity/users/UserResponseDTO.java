package com.desafio2_zetta.todo_list.entity.users;

public record UserResponseDTO(Long id, String name, String email, UserRole role) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}