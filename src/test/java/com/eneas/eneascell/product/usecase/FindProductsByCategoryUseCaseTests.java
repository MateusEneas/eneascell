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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FindProductsByCategoryUseCaseTests {

    @InjectMocks
    private FindProductsByCategoryUseCase useCase;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Test
    void shouldReturnProductsByCategory() {
        UUID categoryId = UUID.randomUUID();

        Product product = new Product();
        ProductDTO dto = new ProductDTO();

        when(repository.findByCategoriesId(categoryId)).thenReturn(List.of(product));
        when(mapper.toDTO(List.of(product))).thenReturn(List.of(dto));

        List<ProductDTO> result = useCase.execute(categoryId);

        assertEquals(1, result.size());

    }

}
