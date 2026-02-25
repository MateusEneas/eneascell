package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.mapper.CategoryMapper;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListCategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    @Transactional(readOnly = true)
    public List<CategoryDTO> execute() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
