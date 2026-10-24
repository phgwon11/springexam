package com.example.pos.repository;

import com.example.pos.dto.ProductResponse;
import com.example.pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IProductRepository
        extends JpaRepository<Product, Long> {

    @Query("""
            SELECT new com.example.pos.dto.ProductResponse(
                p.id, p.name, p.code, p.unitPrice, c.name, c.id)
            FROM Product p
            LEFT JOIN p.category c
            WHERE (:name IS NULL OR LOWER(p.name)
                   LIKE LOWER(CONCAT('%', :name, '%')))
              AND (:minPrice IS NULL OR p.unitPrice >= :minPrice)
              AND (:maxPrice IS NULL OR p.unitPrice <= :maxPrice)
              AND (:categoryId IS NULL OR c.id = :categoryId)
            """)
    List<ProductResponse> filterProducts(
            @Param("name") String name,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("categoryId") Long categoryId
    );
}
