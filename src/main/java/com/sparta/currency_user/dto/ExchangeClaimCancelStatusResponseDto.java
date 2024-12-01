package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.ExchangeClaimStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExchangeClaimCancelStatusResponseDto {
    Long id;
    ExchangeClaimStatus status;
    LocalDateTime modifiedAt;

    public ExchangeClaimCancelStatusResponseDto(Long id, ExchangeClaimStatus status, LocalDateTime modifiedAt) {
        this.id = id;
        this.status = status;
        this.modifiedAt = modifiedAt;
    }
}
