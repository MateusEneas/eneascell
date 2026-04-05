package com.eneas.eneascell.auth.controller;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.dto.CreateUserDTO;
import com.eneas.eneascell.auth.dto.UpdateUserDTO;
import com.eneas.eneascell.auth.dto.UserResponseDTO;
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
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid CreateUserDTO dto) {
        return ResponseEntity.ok(createUserUseCase.execute(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        return ResponseEntity.ok(listUsersUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id,
                                       @RequestBody @Valid UpdateUserDTO dto) {
        return ResponseEntity.ok(updateUserUseCase.execute(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
