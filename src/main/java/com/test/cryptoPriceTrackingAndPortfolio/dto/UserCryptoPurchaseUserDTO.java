package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.test.cryptoPriceTrackingAndPortfolio.enums.BuySell;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserCryptoPurchaseUserDTO {
    private CryptoDTO userCryptoPurchaseCrypto;
    private BigDecimal price;
    private BigDecimal amount;
    private BuySell buyOrSell;
}
