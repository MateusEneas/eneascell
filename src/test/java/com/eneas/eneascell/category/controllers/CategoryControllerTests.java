package com.eneas.eneascell.category.controllers;

import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.usecase.*;
import com.eneas.eneascell.exceptions.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(GlobalExceptionHandler.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateCategoryUseCase createCategoryUseCase;

    @MockitoBean
    private DeleteCategoryByIdUseCase deleteCategoryByIdUseCase;

    @MockitoBean
    private ListByIdCategoryUseCase listByIdCategoryUseCase;

    @MockitoBean
    private ListCategoryUseCase listCategoryUseCase;

    @MockitoBean
    private UpdateCategoryUseCase updateCategoryUseCase;

    @Test
    void shouldCreateCategory() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        dto.setNome("Categoria Teste");

        when(createCategoryUseCase.execute(any())).thenReturn(dto);

        mockMvc.perform(post("/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "nome": "Categoria Teste"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Categoria Teste"));
    }

    @Test
    void shouldReturnCategoryList() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        dto.setNome("Categoria");

        when(listCategoryUseCase.execute()).thenReturn(List.of(dto));

        mockMvc.perform(get("/categories/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Categoria"));

    }

    @Test
    void shouldReturnCategoryById() throws Exception {
        UUID id = UUID.randomUUID();

        CategoryDTO dto = new CategoryDTO();
        dto.setNome("Categoria");

        when(listByIdCategoryUseCase.execute(id)).thenReturn(dto);

        mockMvc.perform(get("/categories/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Categoria"));
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/categories/{id}", id))
                .andExpect(status().isNoContent());

        verify(deleteCategoryByIdUseCase).execute(id);
    }
}
