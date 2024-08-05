package com.test.cryptoPriceTrackingAndPortfolio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
}
