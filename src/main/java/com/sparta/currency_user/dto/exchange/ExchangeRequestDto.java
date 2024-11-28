package com.sparta.currency_user.dto.exchange;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    private final Long userId;

    private final Long currencyId;

    private final BigDecimal amountInKrw;

    public ExchangeRequestDto(Long userId, Long currencyId, BigDecimal amountInKrw) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
    }
}
