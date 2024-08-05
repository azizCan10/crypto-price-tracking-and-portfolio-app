package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.cryptoPriceTrackingAndPortfolio.enums.BuySell;
import com.test.cryptoPriceTrackingAndPortfolio.model.Crypto;
import com.test.cryptoPriceTrackingAndPortfolio.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserCryptoOperationHistoryDTO {
    private Long id;

    @JsonProperty("user")
    private User userCryptoPurchaseUser;

    @JsonProperty("crypto")
    private Crypto userCryptoPurchaseCrypto;

    private BigDecimal price;
    private BigDecimal amount;
    private BuySell buyOrSell;
}
