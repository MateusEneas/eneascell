package com.eneas.eneascell.auth.usecase;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.dto.CreateUserDTO;
import com.eneas.eneascell.auth.dto.UserResponseDTO;
import com.eneas.eneascell.auth.repository.UserRepository;
import com.eneas.eneascell.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO execute(CreateUserDTO dto) {

        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new BusinessException("Email já cadastrado");
        }

        User user = new User();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setSenha(passwordEncoder.encode(dto.senha()));
        user.setRole(dto.role());

        return UserResponseDTO.from(userRepository.save(user));
    }


}
