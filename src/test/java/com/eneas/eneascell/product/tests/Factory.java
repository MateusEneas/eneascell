package com.eneas.eneascell.product.tests;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;

import java.math.BigDecimal;

public class Factory {

    public static Product createProduct() {
        Product product = new Product();
        product.setNome("Produto Teste");
        product.setPreco(BigDecimal.TEN);
        product.setQuantidade(1);
        product.setDescricao("Teste");
        product.getCategories().add(new Category());

        return product;
    }

    public static ProductDTO createProductDto() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
