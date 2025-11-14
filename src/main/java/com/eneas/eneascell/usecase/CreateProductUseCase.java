package com.eneas.eneascell.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.domain.Product;
import com.eneas.eneascell.repositories.ProductRepository;

@Service
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public Product execute(Product product) {
        return this.productRepository.save(product);
    }

}
