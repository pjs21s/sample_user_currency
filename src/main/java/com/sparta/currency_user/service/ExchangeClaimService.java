package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeClaimCancelStatusResponseDto;
import com.sparta.currency_user.dto.ExchangeClaimRequestDto;
import com.sparta.currency_user.dto.ExchangeClaimResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.ExchangeClaim;
import com.sparta.currency_user.entity.ExchangeClaimStatus;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.global.CustomException;
import com.sparta.currency_user.global.ErrorCodeEnum;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeClaimRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeClaimService {

    public final ExchangeClaimRepository exchangeClaimRepository;
    public final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public ExchangeClaimResponseDto save(ExchangeClaimRequestDto exchangeClaimRequestDto) {


        User findUser = userRepository.findById(exchangeClaimRequestDto.getUserId()).orElseThrow(() -> new CustomException(ErrorCodeEnum.USER_NOT_FOUND));
        Currency findCurrency = currencyRepository.findById(exchangeClaimRequestDto.getCurrencyId()).orElseThrow(() -> new IllegalArgumentException("통화를 찾을 수 없습니다."));
        BigDecimal amountInKrw = exchangeClaimRequestDto.getAmountInKrw();
        BigDecimal exchangeRate = findCurrency.getExchangeRate();
        BigDecimal amountAfterKrw = amountInKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);


        ExchangeClaim exchangeClaim = new ExchangeClaim(findUser, findCurrency, amountInKrw, amountAfterKrw, ExchangeClaimStatus.NORMAL);

        ExchangeClaim savedExchangeClaim = exchangeClaimRepository.save(exchangeClaim);
        return new ExchangeClaimResponseDto(savedExchangeClaim);
    }

    public List<ExchangeClaimResponseDto> getExchangeClaimsByUserId(Long userId) {
        List<ExchangeClaim> exchangeClaimsByUserId = exchangeClaimRepository.findExchangeClaimsByUserId(userId);
        return exchangeClaimsByUserId.stream().map(exchangeClaim -> new ExchangeClaimResponseDto(exchangeClaim)).toList();
    }

    @Transactional
    public ExchangeClaimCancelStatusResponseDto cancelExchangeClaim(Long exchangeClaimId) {
        Optional<ExchangeClaim> byId = exchangeClaimRepository.findById(exchangeClaimId);
        ExchangeClaim exchangeClaim = byId.orElseThrow(
                () -> new CustomException(ErrorCodeEnum.EXCHANGE_CLAIM_NOT_FOUND)
        );
        exchangeClaim.updateStatus();

        return new ExchangeClaimCancelStatusResponseDto(exchangeClaim.getId(), exchangeClaim.getStatus(), exchangeClaim.getModifiedAt());
    }
}
