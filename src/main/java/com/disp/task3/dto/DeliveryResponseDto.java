package com.disp.task3.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponseDto {

    private Long id;
    private Long supplierId;
    private String supplierName;
    private LocalDateTime deliveryDateTime;
    private String deliveryNumber;
    private List<DeliveryItemResponseDto> items;
    private BigDecimal totalWeight;
    private BigDecimal totalCost;
}