package com.test.cryptoPriceTrackingAndPortfolio.model;

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
public class OperationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User operationHistoryUser;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto operationHistoryCrypto;

    private BigDecimal price;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private BuySell buyOrSell;
}
