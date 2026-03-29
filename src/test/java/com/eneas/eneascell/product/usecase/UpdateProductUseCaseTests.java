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

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UpdateProductUseCaseTests {

    @InjectMocks
    private UpdateProductUseCase useCase;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    private UUID id;
    private Product product;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        product = new Product();
        product.setId(id);
        product.setNome("Antigo");
        product.setPreco(BigDecimal.TEN);
    }

    @Test
    void shouldUpdateProductSuccessfully() {
        ProductDTO dto = new ProductDTO();
        dto.setNome("Novo");

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);
        when(mapper.toDTO(product)).thenReturn(dto);

        ProductDTO result = useCase.execute(id, dto);

        assertEquals("Novo", product.getNome());
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(id, new ProductDTO()));
    }






}
