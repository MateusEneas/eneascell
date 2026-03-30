package com.eneas.eneascell.category.repositories;

import com.eneas.eneascell.category.domain.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

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

    @Test
    public void findByIdShouldReturnAnExistingId() {
        Category category = new Category();
        category.setNome("Categoria");

        category = repository.save(category);

        Optional<Category> result = repository.findById(category.getId());

        Assertions.assertTrue(result.isPresent());

    }

    @Test
    public void findByIdShouldReturnEmptyWhenTheIdDoesNotExist() {
        UUID nonExistingId = UUID.randomUUID();

        Optional<Category> result = repository.findById(nonExistingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        Category category = new Category();
        category.setNome("Categoria");

        category = repository.save(category);

        UUID id = category.getId();

        repository.deleteById(id);

        Optional<Category> result = repository.findById(id);
        Assertions.assertFalse(result.isPresent());

    }
}
