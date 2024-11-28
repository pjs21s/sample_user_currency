package com.sparta.currency_user.dto.exchange;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotNull(message = "userId 은 필수값 입니다.")
    private final Long userId;

    @NotNull(message = "currencyId 은 필수값 입니다.")
    private final Long currencyId;

    @NotNull(message = "amountInKrw 은 필수값 입니다.")
    private final BigDecimal amountInKrw;

    public ExchangeRequestDto(Long userId, Long currencyId, BigDecimal amountInKrw) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
    }
}
