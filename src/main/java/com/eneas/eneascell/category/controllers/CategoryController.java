package com.eneas.eneascell.category.controllers;

import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.usecase.ListByIdCategoryUseCase;
import com.eneas.eneascell.category.usecase.ListCategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private ListCategoryUseCase listCategoryUseCase;

    @Autowired
    private ListByIdCategoryUseCase listByIdCategoryUseCase;

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

}
