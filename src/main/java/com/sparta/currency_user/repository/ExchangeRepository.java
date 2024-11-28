package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.exchange.ExchangeCountResponseDto;
import com.sparta.currency_user.entity.Exchange;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    @Query(value = "select count(ex.id) as totalCount , sum(ex.amountInKrw) as totalAmountInKrw " +
            "from Exchange as ex " +
            "where ex.user.id = :userId and ex.currency.id = :currencyId ")
    List<ExchangeCountResponseDto> findTotalExchange(
            @Param("userId") Long userId,
            @Param("currencyId") Long currencyId);

}
