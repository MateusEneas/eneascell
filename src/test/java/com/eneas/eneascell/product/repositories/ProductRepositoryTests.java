package com.eneas.eneascell.product.repositories;

import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveShouldPersistIdWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);

        product = productRepository.save(product);

        Assertions.assertNotNull(product.getId());
    }

    @Test
    public void findByIdShouldReturnAnExistingId() {

        Product product = Factory.createProduct();

        product= productRepository.save(product);

        Optional<Product> result = productRepository.findById(product.getId());

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyWhenTheIdDoesNotExist() {

        UUID nonExistentId = UUID.randomUUID();

        Optional<Product> result = productRepository.findById(nonExistentId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        Product product = Factory.createProduct();

        product = productRepository.save(product);

        UUID id = product.getId();

        productRepository.deleteById(id);

        Optional<Product> result = productRepository.findById(id);
        Assertions.assertFalse(result.isPresent());
    }

}
