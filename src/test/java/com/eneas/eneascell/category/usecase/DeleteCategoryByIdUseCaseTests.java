package com.eneas.eneascell.category.usecase;

import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DeleteCategoryByIdUseCaseTests {

    @InjectMocks
    private DeleteCategoryByIdUseCase useCase;

    @Mock
    private CategoryRepository repository;

    private UUID existingId;
    private UUID nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
    }

    @Test
    void shouldDeleteWhenIdExists() {
        when(repository.existsById(existingId)).thenReturn(true);
        useCase.execute(existingId);
        verify(repository).deleteById(existingId);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        when(repository.existsById(nonExistingId)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> useCase.execute(nonExistingId));
    }
}
