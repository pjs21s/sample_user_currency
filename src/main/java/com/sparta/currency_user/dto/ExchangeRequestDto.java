package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {
    private Long user_id;
    private BigDecimal amount_in_krw;
    private Long to_currency_id;
    private String status;

    public ExchangeRequestDto(Long user_id) {
        this.user_id = user_id;
    }

    public ExchangeRequestDto(String status) {
        this.status = status;
    }

    public ExchangeRequestDto() {
    }
}

