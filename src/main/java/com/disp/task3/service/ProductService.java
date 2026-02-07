package com.disp.task3.service;

import com.disp.task3.model.Product;
import com.disp.task3.model.enums.ProductType;
import com.disp.task3.model.enums.ProductVariety;
import com.disp.task3.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    /**
     * Проверка соответствия сорта и типа продукции
     */
    public boolean isValidVarietyTypeCombination(ProductVariety variety, ProductType type) {
        return variety.getProductType() == type;
    }

    /**
     * Получение продукта с валидацией
     */
    public Product getProductWithValidation(Long productId, BigDecimal weight, BigDecimal pricePerKg) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Продукт с ID " + productId + " не найден"));

        // Валидация соответствия сорта и типа
        if (!isValidVarietyTypeCombination(product.getVariety(), product.getType())) {
            throw new IllegalArgumentException(
                    "Некорректная комбинация сорта '" + product.getVariety().getDisplayName() +
                            "' и типа '" + product.getType().getDisplayName() + "'");
        }

        // Валидация веса и цены
        if (weight == null || weight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Вес должен быть положительным числом");
        }

        if (pricePerKg == null || pricePerKg.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Цена за кг должна быть положительной");
        }

        return product;
    }
}
