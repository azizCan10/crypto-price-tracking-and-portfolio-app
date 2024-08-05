package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;

    @JsonProperty("tracking")
    private Set<CryptoDTO> userCryptoTracks;

    @JsonProperty("portfolio")
    private List<UserCryptoPurchaseUserDTO> userCryptoPurchases;
}
