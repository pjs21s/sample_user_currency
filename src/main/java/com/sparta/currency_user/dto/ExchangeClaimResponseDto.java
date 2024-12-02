package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.ExchangeClaim;
import com.sparta.currency_user.entity.ExchangeClaimStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeClaimResponseDto {





    private Long id;
    private BigDecimal amountInKrw;
    private BigDecimal amountAfterExchange;
    private String currencyName;
    private ExchangeClaimStatus status;
    private LocalDateTime createdAt;

    public ExchangeClaimResponseDto(ExchangeClaim exchangeClaim) {
        this.id = exchangeClaim.getId();
        this.currencyName = exchangeClaim.getCurrency().getCurrencyName();
        this.amountInKrw = exchangeClaim.getAmountInKrw();
        this.amountAfterExchange = exchangeClaim.getAmountAfterExchange();
        this.status = exchangeClaim.getStatus();
        this.createdAt = exchangeClaim.getCreatedAt();

    }
}