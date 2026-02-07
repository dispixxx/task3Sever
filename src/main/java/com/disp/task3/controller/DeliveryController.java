package com.disp.task3.controller;

import com.disp.task3.dto.DeliveryCreateDto;
import com.disp.task3.dto.DeliveryResponseDto;
import com.disp.task3.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.net.URI.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryResponseDto> createDelivery(
            @Valid @RequestBody DeliveryCreateDto dto) {

        var delivery = deliveryService.createDelivery(dto);
        var response = deliveryService.mapToResponse(delivery);

        return ResponseEntity
                .created(create("/api/deliveries/" + delivery.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto> getDelivery(@PathVariable Long id) {
        var delivery = deliveryService.getDeliveryById(id);
        var response = deliveryService.mapToResponse(delivery);
        return ResponseEntity.ok(response);
    }
}
