package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.mapper.CategoryMapper;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ListByIdCategoryUseCase {

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private CategoryMapper mapper;

        public CategoryDTO execute(UUID id) {

            var category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Categoria não encontrada!"));

            return mapper.toDTO(category);
        }
}
