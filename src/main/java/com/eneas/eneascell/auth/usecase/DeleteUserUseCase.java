package com.eneas.eneascell.auth.usecase;


import com.eneas.eneascell.auth.repository.UserRepository;
import com.eneas.eneascell.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public void execute(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }

}
