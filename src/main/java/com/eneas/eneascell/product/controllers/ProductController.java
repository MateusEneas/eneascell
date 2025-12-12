package com.eneas.eneascell.product.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.usecase.CreateProductUseCase;
import com.eneas.eneascell.product.usecase.DeleteByIdUseCase;
import com.eneas.eneascell.product.usecase.FilterProductUseCase;
import com.eneas.eneascell.product.usecase.ListByIdProductUseCase;
import com.eneas.eneascell.product.usecase.ListProductPaginatedUseCase;
import com.eneas.eneascell.product.usecase.ListProductUseCase;
import com.eneas.eneascell.product.usecase.UpdateProductUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private CreateProductUseCase createProductUseCase;

    @Autowired
    private ListProductUseCase listProductUseCase;

    @Autowired
    private ListProductPaginatedUseCase listProductPaginatedUseCase;

    @Autowired
    private ListByIdProductUseCase listByIdProductUseCase;

    @Autowired
    private DeleteByIdUseCase deleteByIdUseCase;

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @Autowired
    private FilterProductUseCase filterProductUseCase;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {

        var result = this.createProductUseCase.execute(productDto);
        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> listProduct() {
        var result = this.listProductUseCase.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> listById(@PathVariable UUID id) {
        ProductDTO dto = listByIdProductUseCase.execute(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDTO>> paginate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<ProductDTO> result = listProductPaginatedUseCase.execute(pageRequest);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductDTO>> filterProducts(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortBy);

        Page<ProductDTO> result = filterProductUseCase.execute(nome, precoMin, precoMax, pageable);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        deleteByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductDTO dto) {
        ProductDTO update = updateProductUseCase.execute(id, dto);
        return ResponseEntity.ok().body(update);
    }

}
