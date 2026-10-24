package com.example.pos.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DailySalesService {
    BigDecimal getDailySales(LocalDate date);
}