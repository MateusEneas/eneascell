package com.eneas.eneascell.product.useCase;

import com.eneas.eneascell.exceptions.NotFoundException;
import com.eneas.eneascell.product.repositories.ProductRepository;
import com.eneas.eneascell.product.usecase.DeleteProductByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DeleteProductByIdUseCaseTests {

    @InjectMocks
    private DeleteProductByIdUseCase deleteProductByIdUseCase;

    @Mock
    private ProductRepository productRepository;

    private UUID existingId;
    private UUID nonExixtingId;

    @BeforeEach
    void setUp() {
        existingId = UUID.randomUUID();
        nonExixtingId = UUID.randomUUID();
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        when(productRepository.existsById(existingId)).thenReturn(true);

        deleteProductByIdUseCase.execute(existingId);

        verify(productRepository).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowNotFoundExceptionWhenIdDoesNotExist() {
        when(productRepository.existsById(nonExixtingId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> {
            deleteProductByIdUseCase.execute(nonExixtingId);
        });
    }

}
