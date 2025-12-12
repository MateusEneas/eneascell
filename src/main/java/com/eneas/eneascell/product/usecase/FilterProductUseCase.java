package com.eneas.eneascell.product.usecase;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
import com.eneas.eneascell.product.repositories.specifications.ProductSpecification;

@Service
public class FilterProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public Page<ProductDTO> execute(String nome,
            BigDecimal precoMin,
            BigDecimal precoMax,
            Pageable pageable) {

        Specification<Product> spec = Specification.unrestricted();

        if (nome != null && !nome.trim().isEmpty()) {
            spec = spec.and(ProductSpecification.nomeContains(nome));
        }

        if (precoMin != null) {
            spec = spec.and(ProductSpecification.precoMinimo(precoMin));
        }

        if (precoMax != null) {
            spec = spec.and(ProductSpecification.precoMaximo(precoMax));
        }

        Page<Product> entityPage = productRepository.findAll(spec, pageable);
        return entityPage.map(mapper::toDTO);
    }

}
