package com.test.cryptoPriceTrackingAndPortfolio.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
