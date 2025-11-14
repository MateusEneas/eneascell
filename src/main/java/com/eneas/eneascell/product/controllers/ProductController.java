package com.eneas.eneascell.product.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.usecase.CreateProductUseCase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private CreateProductUseCase createProductUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDTO productDto) {

        Product product = new Product();
        product.setNome(productDto.getNome());
        product.setPreco(productDto.getPreco());
        product.setQuantidade(productDto.getQuantidade());
        product.setDescricao(productDto.getDescricao());
        var result = this.createProductUseCase.execute(product);
        return ResponseEntity.ok().body(result);

    }

}
