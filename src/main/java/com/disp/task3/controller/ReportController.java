package com.disp.task3.controller;

import com.disp.task3.dto.SupplierReportDto;
import com.disp.task3.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/suppliers")
    public ResponseEntity<SupplierReportDto> getSupplierReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // Если endDate не указан, используем сегодняшнюю дату
        if (endDate == null) {
            endDate = LocalDate.now();
        }

        // Валидация дат
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Дата начала (" + startDate + ") не может быть позже даты окончания (" + endDate + ")");
        }

        var report = reportService.generateSupplierReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
