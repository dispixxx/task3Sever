-- Очистка данных
DELETE FROM delivery_items;
DELETE FROM deliveries;
DELETE FROM products;
DELETE FROM suppliers;

-- Сброс последовательностей
ALTER SEQUENCE suppliers_id_seq RESTART WITH 1;
ALTER SEQUENCE products_id_seq RESTART WITH 1;
ALTER SEQUENCE deliveries_id_seq RESTART WITH 1;
ALTER SEQUENCE delivery_items_id_seq RESTART WITH 1;

-- Поставщики (3 поставщика)
INSERT INTO suppliers (name, info)
VALUES ('ООО "Фруктовоз"', 'тел: +7 (495) 123-45-67, email: info@fruktoz.ru');

INSERT INTO suppliers (name, info)
VALUES ('АО "Садовод-Плюс"', 'тел: +7 (495) 234-56-78, email: sales@sadovod-plus.ru');

INSERT INTO suppliers (name, info)
VALUES ('ИП Петров А.В.', 'тел: +7 (495) 345-67-89, email: petrov@fruits-market.ru');

-- Продукты: яблоки
INSERT INTO products (name, type, variety, default_price, description)
VALUES ('Яблоки Голден Делишес', 'APPLE', 'GOLDEN_DELICIOUS', 120.00, 'Сладкие золотистые яблоки, ранний сорт');

INSERT INTO products (name, type, variety, default_price, description)
VALUES ('Яблоки Гренни Смит', 'APPLE', 'GRANNY_SMITH', 135.00, 'Кисло-сладкие зелёные яблоки, поздний сорт');

INSERT INTO products (name, type, variety, default_price, description)
VALUES ('Яблоки Гала', 'APPLE', 'GALA', 115.00, 'Сладкие красные яблоки, универсальный сорт');

INSERT INTO products (name, type, variety, default_price, description)
VALUES ('Яблоки Фуджи', 'APPLE', 'FUJI', 145.00, 'Очень сладкие хрустящие яблоки, поздний сорт');

-- Продукты: груши
INSERT INTO products (name, type, variety, default_price, description)
VALUES ('Груши Вильямс', 'PEAR', 'WILLIAMS', 155.00, 'Сочные сладкие груши, летний сорт');

INSERT INTO products (name, type, variety, default_price, description)
VALUES ('Груши Конференция', 'PEAR', 'CONFERENCE', 165.00, 'Длинные сладкие груши, универсальный сорт');

INSERT INTO products (name, type, variety, default_price, description)
VALUES ('Груши Дюшес', 'PEAR', 'DUCHESSE', 175.00, 'Крупные ароматные груши, осенний сорт');