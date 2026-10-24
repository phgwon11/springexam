
        package com.example.pos.service;

import com.example.pos.dto.ProductRequest;
import com.example.pos.dto.ProductResponse;
import com.example.pos.network.ResponseData;

public interface ProductService {

    ResponseData<ProductResponse> createProduct(ProductRequest request);

}
