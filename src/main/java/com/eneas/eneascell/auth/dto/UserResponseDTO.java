package com.eneas.eneascell.auth.dto;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.domain.UserRole;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String nome,
        String email,
        UserRole role
) {

    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getRole()
        );
    }
}
