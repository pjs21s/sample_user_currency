package com.sparta.currency_user.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeClaimRequestDto {
    @NotNull(message = "userId 값은 필수입니다")
    Long userId;
    @NotNull(message = "currencyId 값은 필수입니다")
    Long currencyId;

    @NotNull(message = "Amount in KRW 값은 필수입니다")
    @DecimalMin(value = "0.0", inclusive = false, message = "음수가 들어갈 수 없습니다.")
    BigDecimal amountInKrw;


}
