package com.example.pos.service;

import com.example.pos.constant.MessageConstants;
import com.example.pos.dto.ProductRequest;
import com.example.pos.dto.ProductResponse;
import com.example.pos.entity.Category;
import com.example.pos.entity.Product;
import com.example.pos.network.ResponseData;
import com.example.pos.repository.ICategoryRepository;
import com.example.pos.repository.IProductRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public ResponseData<ProductResponse> createProduct(ProductRequest request) {

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElse(null);

        if (category == null) {
            return ResponseData.error(
                    HttpStatus.NOT_FOUND,
                    "Category not found"
            );
        }

        if (!category.isActive()) {
            return ResponseData.error(
                    HttpStatus.BAD_REQUEST,
                    "Category is inactive"
            );
        }

        if (request.getUnitPrice() < 0) {
            return ResponseData.error(
                    HttpStatus.BAD_REQUEST,
                    "Price must be greater than zero"
            );
        }

        if (request.getAvailableQuantity() < 0) {
            return ResponseData.error(
                    HttpStatus.BAD_REQUEST,
                    "Quantity cannot be negative"
            );
        }

        Product product = new Product();

        product.setName(request.getProductName());
        product.setCode(request.getProductCode());

        product.setUnitPrice(
                BigDecimal.valueOf(request.getUnitPrice())
        );

        product.setAvailableQuantity(
                request.getAvailableQuantity()
        );

        product.setActive(request.isActiveStatus());
        product.setCategory(category);


        Product savedProduct = productRepository.save(product);


        ProductResponse productResponse = new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getCode(),
                savedProduct.getUnitPrice(),
                savedProduct.getCategory().getName(),
                savedProduct.getCategory().getId()
        );

        return ResponseData.success(
                productResponse,
                MessageConstants.TEST_CREATED_SUCCESSFULLY
        );
    }
}