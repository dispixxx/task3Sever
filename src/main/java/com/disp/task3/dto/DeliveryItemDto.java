package com.disp.task3.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryItemDto {

    @NotNull(message = "productId обязательный")
    private Long productId;

    @NotNull(message = "weight обязательный")
    @DecimalMin(value = "0.01", message = "Вес должен быть больше 0")
    private BigDecimal weight;

    @NotNull(message = "pricePerKg обязательный")
    @DecimalMin(value = "0.01", message = "Цена должна быть больше 0")
    private BigDecimal pricePerKg;

    public @NotNull(message = "productId обязательный") Long getProductId() {
        return productId;
    }

    public void setProductId(@NotNull(message = "productId обязательный") Long productId) {
        this.productId = productId;
    }

    public @NotNull(message = "weight обязательный") @DecimalMin(value = "0.01", message = "Вес должен быть больше 0") BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(@NotNull(message = "weight обязательный") @DecimalMin(value = "0.01", message = "Вес должен быть больше 0") BigDecimal weight) {
        this.weight = weight;
    }

    public @NotNull(message = "pricePerKg обязательный") @DecimalMin(value = "0.01", message = "Цена должна быть больше 0") BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(@NotNull(message = "pricePerKg обязательный") @DecimalMin(value = "0.01", message = "Цена должна быть больше 0") BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
}
