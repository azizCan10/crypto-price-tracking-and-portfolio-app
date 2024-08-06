package com.test.cryptoPriceTrackingAndPortfolio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String value;

    @Override
    public String getAuthority() {
        return name();
    }
}
