package com.eneas.eneascell.auth.controller;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.dto.CreateUserDTO;
import com.eneas.eneascell.auth.dto.UpdateUserDTO;
import com.eneas.eneascell.auth.dto.UserResponseDTO;
import com.eneas.eneascell.auth.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Operation(summary = "Criar usuário")
    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid CreateUserDTO dto) {
        return ResponseEntity.ok(createUserUseCase.execute(dto));
    }

    @Operation(summary = "Lista todos os usuários")
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        return ResponseEntity.ok(listUsersUseCase.execute());
    }

    @GetMapping("/perfil")
    public ResponseEntity<UserResponseDTO> getPerfil(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserResponseDTO.from(user));
    }

    @Operation(summary = "Listar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }

    @Operation(summary = "Atualizar usuário")
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id,
                                       @RequestBody @Valid UpdateUserDTO dto) {
        return ResponseEntity.ok(updateUserUseCase.execute(id, dto));
    }

    @Operation(summary = "Deletar usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
