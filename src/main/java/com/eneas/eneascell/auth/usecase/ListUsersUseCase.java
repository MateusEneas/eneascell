package com.eneas.eneascell.auth.usecase;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.dto.UserResponseDTO;
import com.eneas.eneascell.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsersUseCase {

    private final UserRepository userRepository;

    public List<UserResponseDTO> execute() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::from)
                .toList();
    }

}
