package com.sparta.currency_user.dto.exchange;

import com.sparta.currency_user.entity.Exchange;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeResponseDto {

    private final Long id;

    private final Long userId;

    private final Long currencyId;

    private final BigDecimal amountInKrw;

    private final BigDecimal amountAfterExchange;

    private final String status;


    public ExchangeResponseDto(Long id, Long userId, Long currencyId, BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public ExchangeResponseDto (Exchange exchange){
        this.id = exchange.getId();
        this.userId = exchange.getUser().getId();
        this.currencyId = exchange.getCurrency().getId();
        this.amountAfterExchange = exchange.getAmountAfterExchange();
        this.amountInKrw = exchange.getAmountInKrw();
        this.status = exchange.getStatus();
    }

    public static ExchangeResponseDto toDto(Exchange exchange) {
        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getUser().getId(),
                exchange.getCurrency().getId(),
                exchange.getAmountInKrw(),
                exchange.getAmountAfterExchange(),
                exchange.getStatus()
        );
    }



}
