package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.category.repositories.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO execute(ProductDTO dto) {

        if (productRepository.findByNome(dto.getNome()) != null)
            throw new BusinessException("Já existe um produto com esse nome!");

        var entity = mapper.toEntity(dto);

        if (dto.getCategoryIds() == null || dto.getCategoryIds().isEmpty()) {
            throw new BusinessException("O produto deve ter pelo menos uma categoria");
        }

        var categories = categoryRepository.findAllById(dto.getCategoryIds());

        if (categories.size() != dto.getCategoryIds().size()) {
            throw new BusinessException("Uma ou mais categorias não existem.");
        }

            entity.getCategories().addAll(categories);

        var saved = productRepository.save(entity);

        return mapper.toDTO(saved);
    }

}
