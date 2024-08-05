package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CryptoDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @PostMapping
    public ResponseEntity<CryptoDTO> createCrypto(@RequestBody CryptoDTO cryptoDTO) {
        return new ResponseEntity<>(cryptoService.createCrypto(cryptoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CryptoDTO>> getAllCryptos() {
        return ResponseEntity.ok(cryptoService.getAllCryptos());
    }
}
