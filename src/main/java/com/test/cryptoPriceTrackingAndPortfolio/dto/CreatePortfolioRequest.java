package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.cryptoPriceTrackingAndPortfolio.enums.BuySell;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreatePortfolioRequest {
    private Long id;

    @JsonProperty("user")
    private IdDTO portfolioUser;

    @JsonProperty("crypto")
    private CryptoDTO portfolioCrypto;

    private BigDecimal price;
    private BigDecimal amount;
    private BuySell buyOrSell;
}
