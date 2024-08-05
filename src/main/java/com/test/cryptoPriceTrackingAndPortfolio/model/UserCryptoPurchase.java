package com.test.cryptoPriceTrackingAndPortfolio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.cryptoPriceTrackingAndPortfolio.enums.BuySell;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserCryptoPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userCryptoPurchaseUser;

    @JsonProperty("crypto")
    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto userCryptoPurchaseCrypto;

    private BigDecimal price;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private BuySell buyOrSell;
}
