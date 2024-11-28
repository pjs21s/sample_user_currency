package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull(message = "currencyName을 입력 해 주세요.")
    private String currencyName;

    @NotNull(message = "exchangeRate을 입력 해 주세요.")
    private BigDecimal exchangeRate;

    @NotNull(message = "symbol을 입력 해 주세요.")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
