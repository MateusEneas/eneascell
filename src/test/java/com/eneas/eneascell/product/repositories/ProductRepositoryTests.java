package com.eneas.eneascell.product.repositories;

import com.eneas.eneascell.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        Product product = new Product();
        product.setNome("Produto Teste");
        product.setPreco(BigDecimal.TEN);
        product.setQuantidade(1);
        product.setDescricao("Teste");

        product = productRepository.save(product);

        UUID id = product.getId();

        productRepository.deleteById(id);

        Optional<Product> result = productRepository.findById(id);
        Assertions.assertFalse(result.isPresent());
    }

}
