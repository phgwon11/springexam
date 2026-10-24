package com.example.pos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private Long productId;
    private String productName;
    private String productCode;
    private BigDecimal unitPrice;
    private String categoryName;
    private Long categoryId;

    public ProductResponse(
            Long productId,
            String productName,
            String productCode,
            BigDecimal unitPrice,
            String categoryName,
            Long categoryId) {

        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }
}