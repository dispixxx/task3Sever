package com.disp.task3.model;

import com.disp.task3.model.enums.ProductType;
import com.disp.task3.model.enums.ProductVariety;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type; // APPLE, PEAR

    @Enumerated(EnumType.STRING)
    private ProductVariety variety; // сорта яблок/груш

    @Column
    private BigDecimal defaultPrice;

    @Column(columnDefinition = "TEXT")
    private String description;
}
