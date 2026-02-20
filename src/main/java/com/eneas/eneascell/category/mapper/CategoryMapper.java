package com.eneas.eneascell.category.mapper;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setNome(category.getNome());
        return dto;
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        Category entity = new Category();
        entity.setNome(categoryDTO.getNome());
        return entity;
    }

}
