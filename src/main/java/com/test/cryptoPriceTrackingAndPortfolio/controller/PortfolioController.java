package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.annotation.LoggedUser;
import com.test.cryptoPriceTrackingAndPortfolio.dto.CreatePortfolioRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.PortfolioDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserDTO;
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
    public ResponseEntity<PortfolioDTO> addOperation(@LoggedUser UserDTO user, @RequestBody CreatePortfolioRequest createPortfolioRequest) {
        return new ResponseEntity<>(portfolioService.addOperation(createPortfolioRequest), HttpStatus.CREATED);
    }
}
