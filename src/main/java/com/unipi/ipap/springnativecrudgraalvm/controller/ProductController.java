package com.unipi.ipap.springnativecrudgraalvm.controller;

import com.unipi.ipap.springnativecrudgraalvm.dto.ProductDto;
import com.unipi.ipap.springnativecrudgraalvm.entity.Product;
import com.unipi.ipap.springnativecrudgraalvm.service.ProductService;
import com.unipi.ipap.springnativecrudgraalvm.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addItem(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(
                Mapper.toDto(productService.addProduct(Mapper.toEntity(productDto))),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/products/add")
    public ResponseEntity<List<ProductDto>> addProducts(@RequestBody List<ProductDto> productDtos) {
        List<Product> productList = productDtos.stream()
                .map(Mapper::toEntity).toList();
        List<ProductDto> productDtoList = productService.saveProducts(productList).stream()
                .map(Mapper::toDto).toList();
        return new ResponseEntity<>(productDtoList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        return ResponseEntity.of(productService.getProductById(id).map(Mapper::toDto));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = productService.getAllProducts().stream()
                .map(Mapper::toDto).toList();
        return ResponseEntity.ok(productDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateItem(@PathVariable UUID id, @RequestBody ProductDto customerDto) {
        return ResponseEntity.ok(Mapper.toDto(productService.modifyProduct(id, Mapper.toEntity(customerDto))));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
