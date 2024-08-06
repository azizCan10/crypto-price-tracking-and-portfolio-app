package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query(
            """
            SELECT p
            FROM Portfolio p
            WHERE p.portfolioUser.id = :userId AND p.portfolioCrypto.id = :cryptoId
            """
    )
    Optional<Portfolio> findByUserAndCrypto(Long userId, Long cryptoId);
}
