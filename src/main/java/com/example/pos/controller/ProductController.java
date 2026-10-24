package com.example.pos.controller;

import com.example.pos.dto.ProductRequest;
import com.example.pos.dto.ProductResponse;
import com.example.pos.network.ResponseData;
import com.example.pos.service.ProductService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseData<ProductResponse> saveProduct(
            @RequestBody ProductRequest request) {

        return productService.createProduct(request);
    }
}
