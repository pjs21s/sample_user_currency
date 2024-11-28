package com.sparta.currency_user.dto.exchange;

import lombok.Getter;

@Getter
public class ExchangeResponseDto {

    private final Long id;

    private final Long userId;

    private final Long currencyId;

    private final Long amountInKrw;

    private final Float amountAfterExchange;

    private final String status;


    public ExchangeResponseDto(Long id, Long userId, Long currencyId, Long amountInKrw, Float amountAfterExchange, String status) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }
}
