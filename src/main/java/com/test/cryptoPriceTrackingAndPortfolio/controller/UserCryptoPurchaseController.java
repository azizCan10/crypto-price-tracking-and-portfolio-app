package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoPurchase;
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
    public ResponseEntity<UserCryptoPurchase> create(@RequestBody UserCryptoPurchase userCryptoPurchase) {
        return new ResponseEntity<>(userCryptoPurchaseService.create(userCryptoPurchase), HttpStatus.CREATED);
    }
}
