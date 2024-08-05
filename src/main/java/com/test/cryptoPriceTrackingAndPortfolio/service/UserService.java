package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.model.User;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
