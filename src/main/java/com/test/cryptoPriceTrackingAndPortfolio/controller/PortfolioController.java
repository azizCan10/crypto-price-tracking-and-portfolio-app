package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.dto.CreatePortfolioDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.PortfolioDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<PortfolioDTO> addOperation(@RequestBody CreatePortfolioDTO createPortfolioDTO) {
        return new ResponseEntity<>(portfolioService.addOperation(createPortfolioDTO), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long userId,
                                       @RequestParam Long cryptoId) {
        portfolioService.delete(userId, cryptoId);
        return ResponseEntity.ok().build();
    }
}