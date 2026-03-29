package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.mapper.CategoryMapper;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateCategoryUseCaseTests {

    @InjectMocks
    private CreateCategoryUseCase useCase;

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    private CategoryDTO dto;
    private Category category;

    @BeforeEach
    void setUp() {
        dto = new CategoryDTO(null, "Eletrônicos");
        category = new Category();
        category.setNome("Eletrônicos");
    }

    @Test
    void shouldCreateCategorySuccessfully() {
        when(repository.findByNome(dto.getNome())).thenReturn(null);
        when(mapper.toEntity(dto)).thenReturn(category);
        when(repository.save(category)).thenReturn(category);
        when(mapper.toDTO(category)).thenReturn(dto);

        CategoryDTO result = useCase.execute(dto);

        assertNotNull(result);
        verify(repository).save(category);
    }

}
