package com.eneas.eneascell.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private UUID id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String nome;


}
