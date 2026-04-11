package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.mapper.CategoryMapper;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UpdateCategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    public CategoryDTO execute(UUID id, CategoryDTO dto) {

        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrado!"));

        if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) {
            if (!dto.getNome().equals(category.getNome())) {
                var categoriaExistente = categoryRepository.findByNome(dto.getNome());
                if (categoriaExistente != null) {
                    throw new BusinessException("Já existe uma categoria com esse nome!");
                }
            }
            category.setNome(dto.getNome());
        }

        Category saved = categoryRepository.save(category);
        return mapper.toDTO(saved);
    }
}
