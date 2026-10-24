package com.example.pos.service;

import com.example.pos.entity.TestStatus;
import com.example.pos.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DailySalesServiceImpl implements DailySalesService {

    private final OrderRepository orderRepository;

    public DailySalesServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public BigDecimal getDailySales(LocalDate date) {
        return orderRepository.getDailySales(
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay(),
                TestStatus.COMPLETED
        );
    }
}