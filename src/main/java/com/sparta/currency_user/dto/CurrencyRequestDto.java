package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull(message = "currencyName 은 필수값 입니다.")
    private String currencyName;

    private BigDecimal exchangeRate;

    @NotNull(message = "symbol 은 필수값 입니다.")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
