package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.category.domain.Category;
import com.eneas.eneascell.category.repositories.CategoryRepository;
import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.mapper.ProductMapper;
import com.eneas.eneascell.product.repositories.ProductRepository;
import com.eneas.eneascell.product.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
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

    @Test
    public void createShouldCallSaveAndReturnProductDTO() {
        UUID categoryId = UUID.randomUUID();
        ProductDTO productDTO = Factory.createProductDto();
        productDTO.setCategoryIds(Set.of(categoryId));;

        Product productToSave = Factory.createProduct();
        Mockito.when(mapper.toEntity(productDTO)).thenReturn(productToSave);

        Category category = new Category();
        category.setId(categoryId);
        category.setNome("Categoria Teste");

        Mockito.when(categoryRepository
                .findAllById(productDTO.getCategoryIds()))
                .thenReturn(List.of(category));

        Product savedProduct = Factory.createProduct();
        savedProduct.setId(UUID.randomUUID());
        savedProduct.getCategories().add(category);

        Mockito.when(productRepository.save(productToSave)).thenReturn(savedProduct);
        Mockito.when(mapper.toDTO(savedProduct)).thenReturn(productDTO);

        ProductDTO result = createProduct.execute(productDTO);

        Mockito.verify(mapper).toEntity(productDTO);
        Mockito.verify(categoryRepository).findAllById(productDTO.getCategoryIds());
        Mockito.verify(productRepository).save(productToSave);
        Mockito.verify(mapper).toDTO(savedProduct);

        Assertions.assertNotNull(result, "O DTO retornado não deve ser null");
        Assertions.assertEquals(productDTO.getNome(), result.getNome());

    }

}
