package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.product.repositories.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CreateProductUseCaseTests {

    @InjectMocks
    private CreateProductUseCase createProduct;

    @Mock
    private ProductRepository productRepository;

}
