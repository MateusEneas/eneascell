package com.eneas.eneascell.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eneas.eneascell.domain.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
