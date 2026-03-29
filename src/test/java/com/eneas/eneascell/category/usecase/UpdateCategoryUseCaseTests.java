package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.mapper.CategoryMapper;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UpdateCategoryUseCaseTests {

    @InjectMocks
    private UpdateCategoryUseCase useCase;

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    private UUID id;
    private Category category;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        category = new Category();
        category.setId(id);
        category.setNome("Antigo");
    }

    @Test
    void shouldUpdateCategory() {
        CategoryDTO dto = new CategoryDTO(null, "Novo");

        when(repository.findById(id)).thenReturn(Optional.of(category));
        when(repository.save(category)).thenReturn(category);
        when(mapper.toDTO(category)).thenReturn(dto);

        CategoryDTO result = useCase.execute(id, dto);

        assertEquals("Novo", category.getNome());
        assertNotNull(result);

    }

    @Test
    void shouldThrowExceptionWhenIdNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(id, new CategoryDTO()));
    }

}
