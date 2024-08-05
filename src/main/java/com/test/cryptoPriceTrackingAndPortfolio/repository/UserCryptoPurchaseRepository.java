package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserCryptoPurchaseRepository extends JpaRepository<UserCryptoPurchase, Long> {

    @Query(
            """
            SELECT u 
            FROM UserCryptoPurchase u
            WHERE u.userCryptoPurchaseUser.id = :userId AND u.userCryptoPurchaseCrypto.id = :cryptoId
            """
    )
    Optional<UserCryptoPurchase> findByUserAndCrypto(Long userId, Long cryptoId);
}
