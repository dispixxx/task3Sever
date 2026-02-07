package com.disp.task3.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierReportDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private List<SupplierReportItemDto> suppliers;
    private BigDecimal totalWeight;
    private BigDecimal totalCost;



}
