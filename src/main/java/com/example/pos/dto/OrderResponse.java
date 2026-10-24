package com.example.pos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponse {

    private Long orderId;
    private BigDecimal totalAmount;
    private Integer loyaltyCoinsEarned;
    private String orderStatus;
}