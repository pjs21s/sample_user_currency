package com.sparta.currency_user.dto.exchange;


import lombok.Getter;

@Getter
public class ExchangeRequestDto {

    private final Long userId;

    private final Long currencyId;

    private final Long amountInKrw;

    public ExchangeRequestDto(Long userId, Long currencyId, Long amountInKrw) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
    }
}
