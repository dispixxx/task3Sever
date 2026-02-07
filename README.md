# task3Sever
 Данные инициализируются из data.sql
 
###############
Запрос на добавление поставки
POST
http://localhost:8080/api/deliveries
body:
{
    "supplierId": 1,
    "deliveryNumber": "FEB-2026-001",
    "items": [
      {
        "productId": 1,
        "weight": 150.50,
        "pricePerKg": 120.00
      },
      {
        "productId": 2,
        "weight": 120.00,
        "pricePerKg": 155.00
      }
    ]
}

###############
Запрос на получение информации о доставке по ID
GET
http://localhost:8080/api/deliveries/{id}

###############
Запрос на получение отчета
GET
http://localhost:8080/api/reports/suppliers?startDate=2026-02-01&endDate=2026-02-28
--Если endDate пустая, то берется сегодняшняя дата

