package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoPurchase;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserCryptoPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCryptoPurchaseService {

    private final UserCryptoPurchaseRepository userCryptoPurchaseRepository;

    public UserCryptoPurchase create(UserCryptoPurchase userCryptoPurchase) {
        return userCryptoPurchaseRepository.save(userCryptoPurchase);
    }
}
