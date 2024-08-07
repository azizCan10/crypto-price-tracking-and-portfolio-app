package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.cryptoPriceTrackingAndPortfolio.enums.BuySell;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OperationHistoryDTO {
    private Long id;

    @JsonProperty("user")
    private IdDTO operationHistoryUser;

    @JsonProperty("crypto")
    private CryptoDTO operationHistoryCrypto;

    private BigDecimal price;
    private BigDecimal amount;
    private BuySell buyOrSell;
}
