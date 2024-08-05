package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoTrackDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.UserCryptoTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-crypto-track")
public class UserCryptoTrackController {

    private final UserCryptoTrackService userCryptoTrackService;

    @PostMapping
    public ResponseEntity<UserCryptoTrackDTO> create(@RequestBody UserCryptoTrackDTO userCryptoTrackDTO) {
        return new ResponseEntity<>(userCryptoTrackService.create(userCryptoTrackDTO), HttpStatus.CREATED);
    }
}
