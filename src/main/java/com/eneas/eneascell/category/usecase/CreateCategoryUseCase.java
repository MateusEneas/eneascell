package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.mapper.CategoryMapper;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateCategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    public CategoryDTO execute(CategoryDTO dto) {

        if (categoryRepository.findByNome(dto.getNome()) != null)
            throw new BusinessException("Já existe uma categoria com esse nome!");

        var entity = mapper.toEntity(dto);
        var saved = categoryRepository.save(entity);

        return mapper.toDTO(saved);
    }

}
