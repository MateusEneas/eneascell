package com.eneas.eneascell.auth.usecase;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.dto.UserResponseDTO;
import com.eneas.eneascell.auth.repository.UserRepository;
import com.eneas.eneascell.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    public UserResponseDTO execute(UUID id) {
        return userRepository.findById(id)
                .map(UserResponseDTO::from)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

}
