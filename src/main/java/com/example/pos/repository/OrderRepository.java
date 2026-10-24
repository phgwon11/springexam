package com.example.pos.repository;

import com.example.pos.entity.Order;
import com.example.pos.entity.TestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderDateTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("""
        SELECT COALESCE(SUM(o.totalAmount), 0)
        FROM Order o
        WHERE o.orderDateTime >= :start
          AND o.orderDateTime < :end
          AND o.orderStatus = :status
        """)
    BigDecimal getDailySales(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("status") TestStatus status
    );
}