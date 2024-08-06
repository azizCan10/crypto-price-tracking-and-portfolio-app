package com.test.cryptoPriceTrackingAndPortfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCryptoTrackDTO {
    private Long id;
    private IdDTO user;
    private CryptoDTO crypto;
}
