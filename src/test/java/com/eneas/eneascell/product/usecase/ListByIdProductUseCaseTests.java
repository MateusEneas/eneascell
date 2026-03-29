package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.exceptions.NotFoundException;
import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
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
public class ListByIdProductUseCaseTests {

    @InjectMocks
    private ListByIdProductUseCase useCase;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
    }

    @Test
    void shouldReturnProduct() {
        Product product = new Product();
        ProductDTO dto = new ProductDTO();

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(mapper.toDTO(product)).thenReturn(dto);

        ProductDTO result = useCase.execute(id);

        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(id));
    }



}
