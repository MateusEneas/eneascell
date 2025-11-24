package com.eneas.eneascell.product.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class ListProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> execute() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductDTO dto = new ProductDTO();
                    dto.setNome(product.getNome());
                    dto.setPreco(product.getPreco());
                    dto.setQuantidade(product.getQuantidade());
                    dto.setDescricao(product.getDescricao());
                    return dto;
                })
                .toList();
    }

}
