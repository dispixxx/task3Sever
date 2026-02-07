package com.disp.task3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierReportItemDto {
    private Long supplierId;
    private String supplierName;
    private List<ProductReportItemDto> products;
    private BigDecimal supplierTotalWeight;
    private BigDecimal supplierTotalCost;
}
