package com.eneas.eneascell.product.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.product.domain.Product;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private BigDecimal preco;

    @NotNull(message = "A quantidade é obrigatória.")
    private Integer quantidade;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotEmpty(message = "O produto deve ter pelo menos uma categoria")
    private Set<UUID> categoryIds;

    private Set<CategoryDTO> category;

    public ProductDTO(Product product, Set<Category> categories) {

    }
}
