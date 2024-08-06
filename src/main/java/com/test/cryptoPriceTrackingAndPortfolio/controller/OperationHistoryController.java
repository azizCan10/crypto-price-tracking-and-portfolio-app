package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.dto.OperationHistoryDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.OperationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/operation-history")
public class OperationHistoryController {

    private final OperationHistoryService operationHistoryService;

    @GetMapping
    public ResponseEntity<List<OperationHistoryDTO>> getAll() {
        return ResponseEntity.ok(operationHistoryService.getAll());
    }
}
