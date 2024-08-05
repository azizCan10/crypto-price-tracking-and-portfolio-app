package com.test.cryptoPriceTrackingAndPortfolio.repository;

import com.test.cryptoPriceTrackingAndPortfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
