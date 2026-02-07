package com.disp.task3.service;

import com.disp.task3.dto.DeliveryCreateDto;
import com.disp.task3.dto.DeliveryItemDto;
import com.disp.task3.dto.DeliveryItemResponseDto;
import com.disp.task3.dto.DeliveryResponseDto;
import com.disp.task3.model.Delivery;
import com.disp.task3.model.DeliveryItem;
import com.disp.task3.model.Product;
import com.disp.task3.model.Supplier;
import com.disp.task3.repo.DeliveryItemRepository;
import com.disp.task3.repo.DeliveryRepository;
import com.disp.task3.repo.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryItemRepository deliveryItemRepository;
    private final SupplierRepository supplierRepository;
    private final ProductService productService;

    @Transactional
    public Delivery createDelivery(DeliveryCreateDto dto) {
        // Валидация и получение поставщика
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Поставщик с ID " + dto.getSupplierId() + " не найден"));

        // Проверка уникальности номера поставки
        if (deliveryRepository.existsByDeliveryNumber(dto.getDeliveryNumber())) {
            throw new IllegalArgumentException(
                    "Поставка с номером '" + dto.getDeliveryNumber() + "' уже существует");
        }

        // Создание поставки
        Delivery delivery = Delivery.builder()
                .supplier(supplier)
                .deliveryDateTime(LocalDateTime.now())
                .deliveryNumber(dto.getDeliveryNumber())
                .items(new ArrayList<>())
                .build();

        // Сохранение для получения ID
        delivery = deliveryRepository.save(delivery);

        // Создание и сохранение позиций
        List<DeliveryItem> createdItems = new ArrayList<>();
        for (DeliveryItemDto itemDto : dto.getItems()) {
            Product product = productService.getProductWithValidation(
                    itemDto.getProductId(),
                    itemDto.getWeight(),
                    itemDto.getPricePerKg()
            );

            BigDecimal totalCost = itemDto.getWeight()
                    .multiply(itemDto.getPricePerKg())
                    .setScale(2, RoundingMode.HALF_UP);

            DeliveryItem item = DeliveryItem.builder()
                    .delivery(delivery)
                    .product(product)
                    .weight(itemDto.getWeight())
                    .pricePerKg(itemDto.getPricePerKg())
                    .totalCost(totalCost)
                    .build();

            DeliveryItem savedItem = deliveryItemRepository.save(item);
            createdItems.add(savedItem);
        }

        // Добавляем сохранённые элементы в delivery
        delivery.setItems(createdItems);

        return delivery;
    }

    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Поставка с ID " + id + " не найдена"));
    }

    public DeliveryResponseDto mapToResponse(Delivery delivery) {

        List<DeliveryItem> items = delivery.getItems() != null ? delivery.getItems() : new ArrayList<>();

        List<DeliveryItemResponseDto> itemsDto = items.stream()
                .map(this::mapItemToResponse)
                .toList();

        BigDecimal totalWeight = itemsDto.stream()
                .map(DeliveryItemResponseDto::getWeight)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCost = itemsDto.stream()
                .map(DeliveryItemResponseDto::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DeliveryResponseDto(
                delivery.getId(),
                delivery.getSupplier().getId(),
                delivery.getSupplier().getName(),
                delivery.getDeliveryDateTime(),
                delivery.getDeliveryNumber(),
                itemsDto,
                totalWeight,
                totalCost
        );
    }

    private DeliveryItemResponseDto mapItemToResponse(DeliveryItem item) {
        return new DeliveryItemResponseDto(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getType().getDisplayName(),
                item.getProduct().getVariety().getDisplayName(),
                item.getWeight(),
                item.getPricePerKg(),
                item.getTotalCost()
        );
    }
}
