package com.eneas.eneascell.product.repositories.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.eneas.eneascell.product.domain.Product;

public class ProductSpecification {

    public static Specification<Product> nomeContains(String nome) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Product> precoMinimo(BigDecimal precoMin) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("preco"), precoMin);
    }

    public static Specification<Product> precoMaximo(BigDecimal precoMax) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("preco"), precoMax);
    }

}
