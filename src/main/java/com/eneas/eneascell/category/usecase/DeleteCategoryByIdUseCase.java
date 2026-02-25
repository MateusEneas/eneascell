package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class DeleteCategoryByIdUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    public void execute(UUID id) {

        boolean exists = categoryRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Categoria não encontrada para exclusão!");
        }

        categoryRepository.deleteById(id);
    }
}
