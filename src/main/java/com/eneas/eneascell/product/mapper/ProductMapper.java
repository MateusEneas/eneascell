package com.eneas.eneascell.product.mapper;

import org.springframework.stereotype.Component;

import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setNome(product.getNome());
        dto.setPreco(product.getPreco());
        dto.setQuantidade(product.getQuantidade());
        dto.setDescricao(product.getDescricao());
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        product.setQuantidade(dto.getQuantidade());
        product.setDescricao(dto.getDescricao());
        return product;
    }
}
