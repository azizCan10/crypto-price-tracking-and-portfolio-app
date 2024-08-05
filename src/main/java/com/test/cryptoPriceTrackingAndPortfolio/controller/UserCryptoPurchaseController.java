package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoPurchaseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.UserCryptoPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-crypto-purchase")
public class UserCryptoPurchaseController {

    private final UserCryptoPurchaseService userCryptoPurchaseService;

    @PostMapping
    public ResponseEntity<UserCryptoPurchaseDTO> create(@RequestBody UserCryptoPurchaseDTO userCryptoPurchaseDTO) {
        return new ResponseEntity<>(userCryptoPurchaseService.create(userCryptoPurchaseDTO), HttpStatus.CREATED);
    }
}
