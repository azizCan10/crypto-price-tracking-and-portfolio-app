package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.UserCryptoTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserCryptoTrackRepository extends JpaRepository<UserCryptoTrack, Long> {

    @Modifying
    @Query(
            """
            DELETE FROM UserCryptoTrack uct
            WHERE uct.user.id = :userId AND uct.crypto.id = :cryptoId
            """
    )
    void deleteByUserIdAndCryptoId(Long userId, Long cryptoId);
}
