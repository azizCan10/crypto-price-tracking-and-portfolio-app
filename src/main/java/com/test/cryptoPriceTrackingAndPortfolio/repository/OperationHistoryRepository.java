package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
    @Modifying
    @Query(
            """
            DELETE FROM OperationHistory o
            WHERE o.operationHistoryUser.id = :userId AND o.operationHistoryCrypto.id = :cryptoId
            """
    )
    void deleteByUserIdAndCryptoId(Long userId, Long cryptoId);
}
