package com.disp.task3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCreateDto {

    @NotNull(message = "supplierId обязательный")
    private Long supplierId;

    @NotBlank(message = "deliveryNumber не может быть пустым")
    @Size(max = 50, message = "Номер поставки не более 50 символов")
    private String deliveryNumber;

    @NotEmpty(message = "Должна быть хотя бы одна позиция")
    @Size(min = 1, max = 100, message = "От 1 до 100 позиций")
    private List<DeliveryItemDto> items;

    //Getter&Setter

    public @NotNull(message = "supplierId обязательный") Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(@NotNull(message = "supplierId обязательный") Long supplierId) {
        this.supplierId = supplierId;
    }

    public @NotBlank(message = "deliveryNumber не может быть пустым") @Size(max = 50, message = "Номер поставки не более 50 символов")
    String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(@NotBlank(message = "deliveryNumber не может быть пустым") @Size(max = 50, message = "Номер поставки не более 50 символов") String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public @NotEmpty(message = "Должна быть хотя бы одна позиция") @Size(min = 1, max = 100, message = "От 1 до 100 позиций")
    List<DeliveryItemDto> getItems() {
        return items;
    }

    public void setItems(@NotEmpty(message = "Должна быть хотя бы одна позиция") @Size(min = 1, max = 100, message = "От 1 до 100 позиций") List<DeliveryItemDto> items) {
        this.items = items;
    }
}
