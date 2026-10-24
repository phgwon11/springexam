package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private LoyaltyCustomer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private TestStatus orderStatus;

    @Column(name = "order_date_time", nullable = false)
    private LocalDateTime orderDateTime;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "loyalty_coin_earned")
    private Integer loyaltyCoinsEarned;

    @PrePersist
    protected void onCreate() {
        if (orderDateTime == null) {
            orderDateTime = LocalDateTime.now();
        }
    }
}



