package com.eneas.eneascell.category.controllers;

import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.usecase.CreateCategoryUseCase;
import com.eneas.eneascell.category.usecase.DeleteCategoryByIdUseCase;
import com.eneas.eneascell.category.usecase.ListByIdCategoryUseCase;
import com.eneas.eneascell.category.usecase.ListCategoryUseCase;
import com.eneas.eneascell.product.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private ListCategoryUseCase listCategoryUseCase;

    @Autowired
    private ListByIdCategoryUseCase listByIdCategoryUseCase;

    @Autowired
    private DeleteCategoryByIdUseCase deleteCategoryByIdUseCase;

    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto) {

        var result = this.createCategoryUseCase.execute(categoryDto);
        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        var result = this.listCategoryUseCase.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> listCategoryById(@PathVariable UUID id) {
        var result = this.listByIdCategoryUseCase.execute(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        deleteCategoryByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }



}
