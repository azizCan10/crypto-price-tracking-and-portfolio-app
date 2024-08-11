package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.annotation.LoggedUser;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserDTO;
import com.test.cryptoPriceTrackingAndPortfolio.enums.Role;
import com.test.cryptoPriceTrackingAndPortfolio.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(@LoggedUser UserDTO user) {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
