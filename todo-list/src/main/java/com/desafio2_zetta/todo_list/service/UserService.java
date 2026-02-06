package com.desafio2_zetta.todo_list.service;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio2_zetta.todo_list.entity.users.RegisterDTO;
import com.desafio2_zetta.todo_list.entity.users.User;
import com.desafio2_zetta.todo_list.entity.users.UserResponseDTO;
import com.desafio2_zetta.todo_list.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponseDTO create(RegisterDTO data) {
        // Valida se o e-mail já existe
        if (this.userRepository.findByEmail(data.email()) != null) {
            throw new RuntimeException("Usuário já cadastrado com este e-mail");
        }

        // Criptografa a senha antes de salvar
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.name(), data.role());

        userRepository.save(newUser);
        return new UserResponseDTO(newUser);
    }

    public List<UserResponseDTO> list() {
        // Retorna a lista de usuários convertida para DTO (sem senhas)
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    public UserResponseDTO update(Long id, RegisterDTO data) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza os campos básicos
        user.setName(data.name());
        user.setEmail(data.email());
        user.setRole(data.role());

        // Se uma nova senha for enviada, ela é criptografada
        if (data.password() != null && !data.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(data.password()));
        }

        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}