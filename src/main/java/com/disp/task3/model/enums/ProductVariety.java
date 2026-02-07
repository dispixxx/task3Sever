package com.disp.task3.model.enums;

import lombok.Getter;

@Getter
/**
 * Сорта яблок и груш
 */
public enum ProductVariety {
    // Сорта яблок
    GOLDEN_DELICIOUS("Голден Делишес", ProductType.APPLE),
    GRANNY_SMITH("Гренни Смит", ProductType.APPLE),
    RED_DELICIOUS("Ред Делишес", ProductType.APPLE),
    GALA("Гала", ProductType.APPLE),
    FUJI("Фуджи", ProductType.APPLE),
    IDARED("Айдаред", ProductType.APPLE),
    ANTONOVKA("Антоновка", ProductType.APPLE),

    // Сорта груш
    WILLIAMS("Вильямс (Бартлетт)", ProductType.PEAR),
    CONFERENCE("Конференция", ProductType.PEAR),
    DUCHESSE("Дюшес", ProductType.PEAR),
    FORELLE("Форель", ProductType.PEAR),
    RED_BARTLETT("Ред Бартлетт", ProductType.PEAR),
    ANJOU("Анжу", ProductType.PEAR);

    private final String displayName;
    private final ProductType productType;

    ProductVariety(String displayName, ProductType productType) {
        this.displayName = displayName;
        this.productType = productType;
    }

}