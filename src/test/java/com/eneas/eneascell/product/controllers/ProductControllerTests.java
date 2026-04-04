package com.eneas.eneascell.product.controllers;

import com.eneas.eneascell.auth.JwtService;
import com.eneas.eneascell.auth.repository.UserRepository;
import com.eneas.eneascell.exceptions.GlobalExceptionHandler;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.usecase.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

@Import(GlobalExceptionHandler.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserRepository userRepository;


    @MockitoBean
    private CreateProductUseCase createProductUseCase;

    @MockitoBean
    private ListProductUseCase listProductUseCase;

    @MockitoBean
    private ListByIdProductUseCase listByIdProductUseCase;

    @MockitoBean
    private DeleteProductByIdUseCase deleteProductByIdUseCase;

    @MockitoBean
    private UpdateProductUseCase updateProductUseCase;

    @MockitoBean
    private FilterProductUseCase filterProductUseCase;

    @MockitoBean
    private FindProductsByCategoryUseCase findProductsByCategoryUseCase;

    @MockitoBean
    private ListProductPaginatedUseCase listProductPaginatedUseCase;

    @Test
    void shouldCreateProduct() throws Exception {

        ProductDTO dto = new ProductDTO();
        dto.setNome("Produto Teste");

        when(createProductUseCase.execute(any())).thenReturn(dto);

        mockMvc.perform(post("/produto/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "nome": "Produto Teste",
                            "preco": 10,
                            "quantidade": 1,
                            "descricao": "Teste",
                            "categoryIds":["123e4567-e89b-12d3-a456-426614174000"]
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Teste"));

    }

    @Test
    void shouldReturnProductList() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setNome("Produto");

        when(listProductUseCase.execute()).thenReturn(List.of(dto));

        mockMvc.perform(get("/produto/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Produto"));
    }

    @Test
    void shouldReturnProductById() throws Exception {

        UUID id = UUID.randomUUID();

        ProductDTO dto = new ProductDTO();
        dto.setNome("Produto");

        when(listByIdProductUseCase.execute(id))
                .thenReturn(dto);

        mockMvc.perform(get("/produto/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto"));
    }

    @Test
    void shouldDeleteProduct() throws Exception {

        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/produto/{id}", id))
                .andExpect(status().isNoContent());

        verify(deleteProductByIdUseCase).execute(id);
    }

    @Test
    void shouldUpdateProduct() throws Exception {

        UUID id = UUID.randomUUID();

        ProductDTO dto = new ProductDTO();
        dto.setNome("Atualizado");

        when(updateProductUseCase.execute(eq(id), any()))
                .thenReturn(dto);

        mockMvc.perform(patch("/produto/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "nome": "Atualizado"
                }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Atualizado"));
    }

    @Test
    void shouldReturnPaginatedProducts() throws Exception {

        ProductDTO dto = new ProductDTO();
        dto.setNome("Produto");

        Page<ProductDTO> page = new PageImpl<>(List.of(dto));

        when(listProductPaginatedUseCase.execute(any()))
                .thenReturn(page);

        mockMvc.perform(get("/produto/page"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("Produto"));
    }

    @Test
    void shouldFilterProducts() throws Exception {

        ProductDTO dto = new ProductDTO();
        dto.setNome("Produto");

        Page<ProductDTO> page = new PageImpl<>(List.of(dto));

        when(filterProductUseCase.execute(any(), any(), any(), any()))
                .thenReturn(page);

        mockMvc.perform(get("/produto/filter")
                        .param("nome", "Prod"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("Produto"));
    }

    @Test
    void shouldReturnProductsByCategory() throws Exception {

        UUID categoryId = UUID.randomUUID();

        ProductDTO dto = new ProductDTO();
        dto.setNome("Produto");

        when(findProductsByCategoryUseCase.execute(categoryId))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/produto/category/{id}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Produto"));
    }

    @Test
    void shouldReturnBadRequestWhenInvalidData() throws Exception {

        mockMvc.perform(post("/produto/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "nome": "",
                    "preco": -1
                }
            """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.nome").exists());
    }

    @Test
    void shouldReturnBadRequestWhenInvalidUUID() throws Exception {

        mockMvc.perform(get("/produto/abc"))
                .andExpect(status().isBadRequest());
    }
}

