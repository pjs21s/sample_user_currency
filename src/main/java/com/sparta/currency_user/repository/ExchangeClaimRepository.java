package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.ExchangeClaim;
import com.sparta.currency_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeClaimRepository extends JpaRepository<ExchangeClaim, Long> {

    List<ExchangeClaim> findExchangeClaimsByUserId(Long userId);
}
