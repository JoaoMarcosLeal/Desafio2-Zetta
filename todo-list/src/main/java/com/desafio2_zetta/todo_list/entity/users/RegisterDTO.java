package com.desafio2_zetta.todo_list.entity.users;

public record RegisterDTO(String email, String password, String name, UserRole role) {

}
