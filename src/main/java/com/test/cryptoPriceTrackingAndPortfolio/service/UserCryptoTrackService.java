package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoTrack;
import com.test.cryptoPriceTrackingAndPortfolio.repository.UserCryptoTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCryptoTrackService {

    private final UserCryptoTrackRepository userCryptoTrackRepository;

    public UserCryptoTrack create(UserCryptoTrack userCryptoTrack) {
        return userCryptoTrackRepository.save(userCryptoTrack);
    }
}
