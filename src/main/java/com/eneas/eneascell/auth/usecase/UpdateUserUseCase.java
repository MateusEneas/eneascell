package com.eneas.eneascell.auth.usecase;


import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.dto.UpdateUserDTO;
import com.eneas.eneascell.auth.repository.UserRepository;
import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(UUID id, UpdateUserDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        if (dto.email() != null && !dto.email().equals(user.getEmail())) {
            if (userRepository.findByEmail(dto.email()).isPresent()) {
                throw new BusinessException("Email já cadastrado");
            }
            user.setEmail(dto.email());
        }

        if (dto.nome() != null) {
            user.setNome(dto.nome());
        }

        if (dto.senha() != null) {
            user.setSenha(passwordEncoder.encode(dto.senha()));
        }

        if (dto.role() != null) {
            user.setRole(dto.role());
        }

        return userRepository.save(user);
    }
}
