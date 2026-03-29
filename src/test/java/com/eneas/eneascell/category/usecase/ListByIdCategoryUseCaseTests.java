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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ListByIdCategoryUseCaseTests {

    @InjectMocks
    private ListByIdCategoryUseCase useCase;

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
    }

    @Test
    void shouldReturnCategory() {

        CategoryDTO dto = new CategoryDTO(id, "Teste");

        when(repository.findById(id)).thenReturn(Optional.of(category));
        when(mapper.toDTO(category)).thenReturn(dto);

        CategoryDTO result = useCase.execute(id);

        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(id));
    }


}
