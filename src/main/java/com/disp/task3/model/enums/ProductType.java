package com.disp.task3.model.enums;

import lombok.Getter;

/**
 * Тип продукции: яблоки или груши
 */
@Getter
public enum ProductType {
    APPLE("Яблоки"),
    PEAR("Груши");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }
}