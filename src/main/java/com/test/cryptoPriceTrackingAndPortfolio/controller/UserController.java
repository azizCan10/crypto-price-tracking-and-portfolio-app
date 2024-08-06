package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.annotation.LoggedUser;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserDTO;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserResponseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(@LoggedUser UserDTO user) {
        return ResponseEntity.ok(userService.getUserById(user.getId()));
    }
}
