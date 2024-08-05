package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoOperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserCryptoOperationHistoryRepository extends JpaRepository<UserCryptoOperationHistory, Long> {
    @Modifying
    @Query(
            """
            DELETE FROM UserCryptoOperationHistory u
            WHERE u.userCryptoPurchaseUser.id = :userId AND u.userCryptoPurchaseCrypto.id = :cryptoId
            """
    )
    void deleteByUserIdAndCryptoId(Long userId, Long cryptoId);
}
