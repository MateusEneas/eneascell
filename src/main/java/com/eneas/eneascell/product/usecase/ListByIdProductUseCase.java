package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.exceptions.NotFoundException;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ListByIdProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public ProductDTO execute(UUID id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));

        return mapper.toDTO(product);
    }
}
