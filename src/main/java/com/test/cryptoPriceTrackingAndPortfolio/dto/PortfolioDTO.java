package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PortfolioDTO {
    private Long id;

    @JsonProperty("user")
    private IdDTO portfolioUser;

    @JsonProperty("crypto")
    private IdDTO portfolioCrypto;

    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal total;
}
