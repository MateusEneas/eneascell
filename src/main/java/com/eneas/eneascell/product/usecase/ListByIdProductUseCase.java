package com.eneas.eneascell.product.usecase;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class ListByIdProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public ProductDTO execute(UUID id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado!"));

        return mapper.toDTO(product);
    }
}
