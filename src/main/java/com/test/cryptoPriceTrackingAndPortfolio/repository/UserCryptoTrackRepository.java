package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCryptoTrackRepository extends JpaRepository<UserCryptoTrack, Long> {
}
