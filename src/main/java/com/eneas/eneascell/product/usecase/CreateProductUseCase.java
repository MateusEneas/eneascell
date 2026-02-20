package com.eneas.eneascell.product.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public ProductDTO execute(ProductDTO dto) {

        if (productRepository.findByNome(dto.getNome()) != null)
            throw new BusinessException("Já existe um produto com esse nome!");

        var entity = mapper.toEntity(dto);
        var saved = productRepository.save(entity);

        return mapper.toDTO(saved);
    }

}
