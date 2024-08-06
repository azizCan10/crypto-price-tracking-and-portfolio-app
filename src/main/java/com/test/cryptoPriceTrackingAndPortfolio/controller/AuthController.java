package com.test.cryptoPriceTrackingAndPortfolio.controller;

import com.test.cryptoPriceTrackingAndPortfolio.dto.AuthRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.CreateUserRequest;
import com.test.cryptoPriceTrackingAndPortfolio.dto.UserResponseDTO;
import com.test.cryptoPriceTrackingAndPortfolio.service.JwtService;
import com.test.cryptoPriceTrackingAndPortfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken(authRequest.getUsername()));
        }

        throw new UsernameNotFoundException("Username not found: " + authRequest.getUsername());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }
}
