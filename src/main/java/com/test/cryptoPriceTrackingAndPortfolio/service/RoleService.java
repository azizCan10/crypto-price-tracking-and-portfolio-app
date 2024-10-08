package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.enums.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    /**
     * This method gets all roles data
     *
     * @return role list
     */
    public List<Role> getAllRoles() {
        return List.of(Role.values());
    }
}
