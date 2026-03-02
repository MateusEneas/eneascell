package com.eneas.eneascell.product.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.eneas.eneascell.product.domain.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    Product findByNome(String nome);

    @Query("SELECT p FROM Product p JOIN FETCH p.categories c WHERE c.id = :categoryId")
    List<Product> findByCategoriesId(UUID categoryId);

}
