package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCryptoPurchaseRepository extends JpaRepository<UserCryptoPurchase, Long> {
}
