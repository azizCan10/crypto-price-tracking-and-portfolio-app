package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.model.Crypto;
import com.test.cryptoPriceTrackingAndPortfolio.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @PostMapping
    public ResponseEntity<Crypto> createCrypto(@RequestBody Crypto crypto) {
        return ResponseEntity.ok(cryptoService.createCrypto(crypto));
    }

    @GetMapping
    public ResponseEntity<List<Crypto>> getAllCryptos() {
        return ResponseEntity.ok(cryptoService.getAllCryptos());
    }
}
