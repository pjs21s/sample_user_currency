package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotBlank(message = "currencyName 은 필수값 입니다.")
    private String currencyName;

    @NotBlank(message = "exchangeRate 은 필수값 입니다.")
    @Min(value = 1) @Max(value = 2000)
    @Digits(integer = 4, fraction = 2)
    private BigDecimal exchangeRate;

    @NotBlank(message = "symbol 은 필수값 입니다.")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
