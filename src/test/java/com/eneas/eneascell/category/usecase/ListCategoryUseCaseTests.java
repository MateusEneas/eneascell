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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ListCategoryUseCaseTests {

    @InjectMocks
    private ListCategoryUseCase useCase;

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    @Test
    void shouldReturnListOfCategories() {

        Category category = new Category();
        category.setNome("Teste");

        CategoryDTO dto = new CategoryDTO(null, "Teste");

        when(repository.findAll()).thenReturn(List.of(category));
        when(mapper.toDTO(category)).thenReturn(dto);

        List<CategoryDTO> result = useCase.execute();

        assertEquals(1, result.size());
    }
}
