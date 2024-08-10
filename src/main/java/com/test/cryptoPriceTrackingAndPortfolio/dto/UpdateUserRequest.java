package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.test.cryptoPriceTrackingAndPortfolio.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UpdateUserRequest {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Set<Role> authorities;
}
