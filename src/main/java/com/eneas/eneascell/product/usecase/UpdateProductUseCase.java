package com.eneas.eneascell.product.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.exceptions.NotFoundException;
import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class UpdateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public ProductDTO execute(UUID id, ProductDTO dto) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));

        if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) {
            product.setNome(dto.getNome());
        }

        if (dto.getPreco() != null) {
            if (dto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("O preço deve ser maior que zero!");
            }
            product.setPreco(dto.getPreco());
        }

        if (dto.getQuantidade() != null) {
            product.setQuantidade(dto.getQuantidade());
        }

        if (dto.getDescricao() != null && !dto.getDescricao().trim().isEmpty()) {
            product.setDescricao(dto.getDescricao());
        }

        Product saved = productRepository.save(product);
        return mapper.toDTO(saved);
    }
}
