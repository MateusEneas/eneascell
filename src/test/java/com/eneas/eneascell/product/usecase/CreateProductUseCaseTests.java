package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
import com.eneas.eneascell.product.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class CreateProductUseCaseTests {

    @InjectMocks
    private CreateProductUseCase createProduct;

    @Mock
    private ProductMapper mapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private ProductDTO validProductDTO;
    private Product productToSave;
    private Product savedProduct;
    private Category category;
    private UUID categoryId;

    @BeforeEach
    void setUp() {
        categoryId = UUID.randomUUID();

        validProductDTO = Factory.createProductDto();
        validProductDTO.setCategoryIds(Set.of(categoryId));

        productToSave = Factory.createProduct();

        category = new Category();
        category.setId(categoryId);
        category.setNome("Categoria Teste");

        savedProduct = Factory.createProduct();
        savedProduct .setId(UUID.randomUUID());
        savedProduct.getCategories().add(category);
    }

    @Test
    public void createShouldSaveProductWhenAllDataIsValid() {

        Mockito.when(mapper.toEntity(validProductDTO)).thenReturn(productToSave);

        Mockito.when(categoryRepository
                .findAllById(validProductDTO.getCategoryIds()))
                .thenReturn(List.of(category));

        Mockito.when(productRepository.save(productToSave)).thenReturn(savedProduct);
        Mockito.when(mapper.toDTO(savedProduct)).thenReturn(validProductDTO);

        ProductDTO result = createProduct.execute(validProductDTO);

        Mockito.verify(mapper).toEntity(validProductDTO);
        Mockito.verify(categoryRepository).findAllById(validProductDTO.getCategoryIds());
        Mockito.verify(productRepository).save(productToSave);
        Mockito.verify(mapper).toDTO(savedProduct);

        Assertions.assertNotNull(result, "O DTO retornado não deve ser null");
        Assertions.assertEquals(validProductDTO.getNome(), result.getNome());

    }

    @Test
    public void createShouldThrowExceptionWhenNoCategory() {
        validProductDTO.setCategoryIds(Set.of());

        BusinessException exception = Assertions.assertThrows(
                BusinessException.class,
                () -> createProduct.execute(validProductDTO)
        );

        Assertions.assertEquals("O produto deve ter pelo menos uma categoria", exception.getMessage());
    }

}
