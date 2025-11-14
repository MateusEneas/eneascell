package com.eneas.eneascell.product.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public Product execute(Product product) {
        return this.productRepository.save(product);
    }

}
