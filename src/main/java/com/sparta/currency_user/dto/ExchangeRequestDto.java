package com.sparta.currency_user.dto;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
public class ExchangeRequestDto {
    private Long userId;

    @NotNull(message = "amount in krw는 Null이 불가능 합니다.")
    private BigDecimal amountInKrw;

    @NotNull(message = "to Currency Id는 Null이 불가능 합니다.")
    private Long toCurrencyId;

    private String status;

    public ExchangeRequestDto(Long userId) {
        this.userId = userId;
    }

    public ExchangeRequestDto(String status) {
        this.status = status;
    }

    public ExchangeRequestDto() {
    }

    public ExchangeRequestDto(Long userId, BigDecimal amountInKrw, Long toCurrencyId) {
        this.userId = userId;
        this.amountInKrw = amountInKrw;
        this.toCurrencyId = toCurrencyId;
    }
}

