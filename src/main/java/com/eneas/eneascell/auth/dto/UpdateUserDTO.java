package com.eneas.eneascell.auth.dto;

import com.eneas.eneascell.auth.domain.UserRole;
import jakarta.validation.constraints.Email;

public record UpdateUserDTO(

        String nome,

        @Email(message = "Email inválido")
        String email,

        String senha,

        UserRole role
) {}
