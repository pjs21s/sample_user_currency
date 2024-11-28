package com.sparta.currency_user.dto.exchange;


import java.math.BigDecimal;

public interface ExchangeCountResponseDto {

    Integer getTotalCount();
    BigDecimal getTotalAmountInKrw();

}
