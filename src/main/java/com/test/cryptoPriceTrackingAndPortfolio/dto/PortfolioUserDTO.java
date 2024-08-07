package com.test.cryptoPriceTrackingAndPortfolio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PortfolioUserDTO {
    private CryptoDTO portfolioCrypto;
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal total;
}
