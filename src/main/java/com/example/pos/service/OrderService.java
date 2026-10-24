package com.example.pos.service;

import com.example.pos.dto.OrderRequest;
import com.example.pos.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);
}