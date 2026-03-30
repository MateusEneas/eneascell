package com.eneas.eneascell.auth.usecase;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsersUseCase {

    private final UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }

}
