package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoOperationHistoryDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.UserCryptoOperationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-crypto-operation-history")
public class UserCryptoOperationHistoryController {

    private final UserCryptoOperationHistoryService userCryptoOperationHistoryService;

    @GetMapping
    public ResponseEntity<List<UserCryptoOperationHistoryDTO>> getAll() {
        return ResponseEntity.ok(userCryptoOperationHistoryService.getAll());
    }
}
