package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private Long id;

    @Column(name = "category_code", nullable = false, unique = true)
    private String code;

    @Column(name = "category_name", length = 100)
    private String name;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active;
}