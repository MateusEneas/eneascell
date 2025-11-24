package com.eneas.eneascell.product.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class ListProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public List<ProductDTO> execute() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

}
