package com.eneas.eneascell.product.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eneas.eneascell.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByNome(String nome);

}
