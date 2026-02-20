package com.eneas.eneascell.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String nome;


}
