package com.eneas.eneascell.category.controllers;

import com.eneas.eneascell.category.dto.CategoryDTO;
import com.eneas.eneascell.category.usecase.ListCategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private ListCategoryUseCase listCategoryUseCase;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategory() {
        var result = this.listCategoryUseCase.execute();
        return ResponseEntity.ok(result);
    }

}
