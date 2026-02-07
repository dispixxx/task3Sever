package com.disp.task3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReportItemDto {
    private Long productId;
    private String productName;
    private String productType;
    private String productVariety;
    private BigDecimal totalWeight;
    private BigDecimal totalCost;
}
