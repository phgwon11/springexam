package com.example.pos.controller;

import com.example.pos.dto.OrderRequest;
import com.example.pos.dto.OrderResponse;
import com.example.pos.service.OrderService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse placeOrder(
            @RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }
}