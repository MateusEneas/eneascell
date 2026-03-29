package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ListProductUseCaseTests {

    @InjectMocks
    private ListProductUseCase useCase;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Test
    void shouldReturnProductList() {
        Product product = new Product();
        ProductDTO dto = new ProductDTO();

        when(repository.findAll()).thenReturn(List.of(product));
        when(mapper.toDTO(product)).thenReturn(dto);

        List<ProductDTO> result = useCase.execute();

        assertEquals(1, result.size());
    }


}
