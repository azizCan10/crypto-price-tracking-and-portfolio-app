package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
}
