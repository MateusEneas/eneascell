package com.eneas.eneascell.auth.controller;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.dto.CreateUserDTO;
import com.eneas.eneascell.auth.dto.UpdateUserDTO;
import com.eneas.eneascell.auth.usecase.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private GetUserByIdUseCase getUserByIdUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserDTO dto) {
        User user = createUserUseCase.execute(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> listAll() {
        List<User> users = listUsersUseCase.execute();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id) {
        User user = getUserByIdUseCase.execute(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id,
                                       @RequestBody @Valid UpdateUserDTO dto) {
        User user = updateUserUseCase.execute(id, dto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
