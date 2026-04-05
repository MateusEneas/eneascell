package com.eneas.eneascell.product.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.eneas.eneascell.product.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private DeleteProductByIdUseCase deleteProductByIdUseCase;

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @Autowired
    private FilterProductUseCase filterProductUseCase;
    @Autowired
    private FindProductsByCategoryUseCase findProductsByCategoryUseCase;

    @Operation(summary = "Criar um produto")
    @PostMapping("/")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {

        var result = this.createProductUseCase.execute(productDto);
        return ResponseEntity.ok().body(result);

    }

    @Operation(summary = "Lista todos os produtos")
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> listProduct() {
        var result = this.listProductUseCase.execute();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Lista produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> listById(@PathVariable UUID id) {
        ProductDTO dto = listByIdProductUseCase.execute(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Listar produto por pagina")
    @GetMapping("/page")
    public ResponseEntity<Page<ProductDTO>> paginate(
            Pageable pageable) {

        int maxSize = 50;

        Pageable safePageable = PageRequest.of(pageable.getPageNumber(),
                Math.min(pageable.getPageSize(), maxSize),
                pageable.getSort());

        Page<ProductDTO> result = listProductPaginatedUseCase.execute(safePageable);

        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Filtrar produto")
    @GetMapping("/filter")
    public ResponseEntity<Page<ProductDTO>> filterProducts(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            Pageable pageable) {

        int maxSize = 50;

        Pageable safePageable = PageRequest.of(pageable.getPageNumber(),
                Math.min(pageable.getPageSize(), maxSize),
                pageable.getSort());

        Page<ProductDTO> result = filterProductUseCase.execute(nome, precoMin, precoMax, safePageable);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Deletar produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        deleteProductByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o produto")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductDTO dto) {
        ProductDTO update = updateProductUseCase.execute(id, dto);
        return ResponseEntity.ok().body(update);
    }

    @Operation(summary = "Lista todos os produtos por categoria")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> findByCategory(@PathVariable UUID categoryId) {

        var result = findProductsByCategoryUseCase.execute(categoryId);
        return ResponseEntity.ok(result);
    }

}
