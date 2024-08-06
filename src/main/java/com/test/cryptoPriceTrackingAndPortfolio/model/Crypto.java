package com.test.cryptoPriceTrackingAndPortfolio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserCryptoTrack> userCryptoTracks;

    @OneToMany(mappedBy = "portfolioCrypto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "operationHistoryCrypto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OperationHistory> operationHistories;
}
