
package com.example.pos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotNull(message = "mandatory")
    private String productName;

    @NotNull(message = "mandatory")
    private String productCode;

    @Positive
    private double unitPrice;

    @Positive
    private Integer availableQuantity;

    private boolean activeStatus;

    private Long categoryId;
}