package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FilterProductUseCaseTests {

    @InjectMocks
    private FilterProductUseCase useCase;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Test
    void shouldFilterProducts() {
        Product product = new Product();
        ProductDTO dto = new ProductDTO();

        Page<Product> page = new PageImpl<>(List.of(product));

        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(mapper.toDTO(product)).thenReturn(dto);

        Page<ProductDTO> result = useCase.execute(
                "Teste",
                BigDecimal.ONE,
                BigDecimal.TEN,
                PageRequest.of(1,10)
        );

        assertEquals(1, result.getContent().size());
    }

}
