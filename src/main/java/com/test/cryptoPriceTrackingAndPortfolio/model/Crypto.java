package com.test.cryptoPriceTrackingAndPortfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @JsonIgnore
    @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserCryptoTrack> userCryptoTracks;
}
