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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;

@ExtendWith(SpringExtension.class)
public class ListProductPaginatedUseCaseTests {

    @InjectMocks
    private ListProductPaginatedUseCase useCase;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Test
    void shouldReturnPagedProducts() {
        Product product = new Product();
        ProductDTO dto = new ProductDTO();

        Page<Product> page = new PageImpl<>(List.of(product));

        when(repository.findAll(any(Pageable.class))).thenReturn(page);
        when(mapper.toDTO(product)).thenReturn(dto);

        Page<ProductDTO> result = useCase.execute(PageRequest.of(0,10));

        assertEquals(1, result.getContent().size());
    }






}
