package com.example.pos.controller;

import com.example.pos.service.DailySalesService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/sales")
public class DailySalesController {

    private final DailySalesService dailySalesService;

    public DailySalesController(DailySalesService dailySalesService) {
        this.dailySalesService = dailySalesService;
    }

    @GetMapping("/daily")
    public BigDecimal getDailySales(
            @RequestParam LocalDate date) {
        return dailySalesService.getDailySales(date);
    }
}