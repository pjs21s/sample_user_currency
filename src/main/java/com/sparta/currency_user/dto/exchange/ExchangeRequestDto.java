package com.sparta.currency_user.dto.exchange;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotBlank(message = "userId 은 필수값 입니다.")
    private final Long userId;

    @NotBlank(message = "currencyId 은 필수값 입니다.")
    private final Long currencyId;

    @NotBlank(message = "amountInKrw 은 필수값 입니다.")
    private final BigDecimal amountInKrw;

    public ExchangeRequestDto(Long userId, Long currencyId, BigDecimal amountInKrw) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
    }
}
