package com.test.cryptoPriceTrackingAndPortfolio.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User portfolioUser;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto portfolioCrypto;

    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal total;
}
