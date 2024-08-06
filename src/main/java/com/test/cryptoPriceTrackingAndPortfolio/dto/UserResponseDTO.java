package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.cryptoPriceTrackingAndPortfolio.enums.Role;
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
    private Set<Role> authorities;

    @JsonProperty("tracking")
    private Set<CryptoDTO> userCryptoTracks;

    @JsonProperty("portfolio")
    private List<PortfolioUserDTO> portfolios;
}
