package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.annotation.LoggedUser;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserCryptoTrackDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.UserCryptoTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-crypto-track")
public class UserCryptoTrackController {

    private final UserCryptoTrackService userCryptoTrackService;

    @PostMapping
    public ResponseEntity<UserCryptoTrackDTO> create(@LoggedUser UserDTO user, @RequestBody UserCryptoTrackDTO userCryptoTrackDTO) {
        return new ResponseEntity<>(userCryptoTrackService.create(user, userCryptoTrackDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cryptoId}")
    public ResponseEntity<Void> deleteByCryptoId(@LoggedUser UserDTO user, @PathVariable Long cryptoId) {
        userCryptoTrackService.deleteByCryptoId(user, cryptoId);
        return ResponseEntity.ok().build();
    }
}
