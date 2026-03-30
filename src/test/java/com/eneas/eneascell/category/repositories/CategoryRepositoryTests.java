package com.eneas.eneascell.category.repositories;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void saveShouldPersistIdWhenIdIsNull() {
        Category category = new Category();
        category.setNome("Categoria");

        category = repository.save(category);

        assertNotNull(category.getId());
    }
}
