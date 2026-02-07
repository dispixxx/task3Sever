package com.disp.task3.service;

import com.disp.task3.dto.ProductReportItemDto;
import com.disp.task3.dto.SupplierReportDto;
import com.disp.task3.dto.SupplierReportItemDto;
import com.disp.task3.model.DeliveryItem;
import com.disp.task3.repo.DeliveryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final DeliveryItemRepository deliveryItemRepository;

    public SupplierReportDto generateSupplierReport(LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = startDate.atStartOfDay();
        // Конец последнего дня: 23:59:59.999999999
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        List<DeliveryItem> items = deliveryItemRepository.findByDeliveryDateBetween(startDateTime, endDateTime);

        // Группировка по поставщикам
        Map<String, List<DeliveryItem>> itemsBySupplier = items.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getDelivery().getSupplier().getName()
                ));

        List<SupplierReportItemDto> suppliers = new ArrayList<>();
        BigDecimal grandTotalWeight = BigDecimal.ZERO;
        BigDecimal grandTotalCost = BigDecimal.ZERO;

        for (Map.Entry<String, List<DeliveryItem>> entry : itemsBySupplier.entrySet()) {
            String supplierName = entry.getKey();
            List<DeliveryItem> supplierItems = entry.getValue();

            // Группировка по продуктам внутри поставщика
            Map<String, List<DeliveryItem>> itemsByProduct = supplierItems.stream()
                    .collect(Collectors.groupingBy(
                            item -> item.getProduct().getName()
                    ));

            List<ProductReportItemDto> products = new ArrayList<>();
            BigDecimal supplierTotalWeight = BigDecimal.ZERO;
            BigDecimal supplierTotalCost = BigDecimal.ZERO;

            for (Map.Entry<String, List<DeliveryItem>> productEntry : itemsByProduct.entrySet()) {
                DeliveryItem deliveryItem = productEntry.getValue().get(0); //Отсортированы по продукту, можно брать любой из доступных
                BigDecimal productWeight = productEntry.getValue().stream()
                        .map(DeliveryItem::getWeight)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal productCost = productEntry.getValue().stream()
                        .map(DeliveryItem::getTotalCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                products.add(new ProductReportItemDto(
                        deliveryItem.getProduct().getId(),
                        deliveryItem.getProduct().getName(),
                        deliveryItem.getProduct().getType().name(),
                        deliveryItem.getProduct().getVariety().name(),
                        productWeight,
                        productCost
                ));

                supplierTotalWeight = supplierTotalWeight.add(productWeight);
                supplierTotalCost = supplierTotalCost.add(productCost);
            }

            suppliers.add(new SupplierReportItemDto(
                    supplierItems.get(0).getDelivery().getSupplier().getId(),
                    supplierName,
                    products,
                    supplierTotalWeight,
                    supplierTotalCost
            ));

            grandTotalWeight = grandTotalWeight.add(supplierTotalWeight);
            grandTotalCost = grandTotalCost.add(supplierTotalCost);
        }

        // Сортировка поставщиков по имени
        suppliers.sort(Comparator.comparing(SupplierReportItemDto::getSupplierName));

        return new SupplierReportDto(
                startDate,
                endDate,
                suppliers,
                grandTotalWeight,
                grandTotalCost
        );
    }
}
