package com.eneas.eneascell.product.usecase;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class ListByIdProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public Optional<ProductDTO> execute(UUID id) {

        return productRepository.findById(id)
                .map(product -> new ProductDTO(
                        product.getNome(),
                        product.getPreco(),
                        product.getQuantidade(),
                        product.getDescricao()));
    }
}
