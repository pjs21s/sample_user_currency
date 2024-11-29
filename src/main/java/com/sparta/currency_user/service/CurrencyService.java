package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.CurrencyRequestDto;
import com.sparta.currency_user.dto.CurrencyResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRepository exchangeRepository;

    public CurrencyResponseDto findById(Long id) {
        return new CurrencyResponseDto(findCurrencyById(id));
    }

    public Currency findCurrencyById(Long id) {
        return currencyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("통화를 찾을 수 없습니다."));
    }

    public List<CurrencyResponseDto> findAll() {
        return currencyRepository.findAll().stream().map(CurrencyResponseDto::toDto).toList();
    }

    @Transactional
    public CurrencyResponseDto save(CurrencyRequestDto currencyRequestDto) {
        if (currencyRequestDto.getExchangeRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("환율은 0 보다 커야합니다." + currencyRequestDto.getExchangeRate());
        }
        Currency savedCurrency = currencyRepository.save(currencyRequestDto.toEntity());
        return new CurrencyResponseDto(savedCurrency);
    }

    //PostConstruct
    @PostConstruct
    public void validExchangeRate() {
        List<Currency> invalidRates = currencyRepository.findInvalidExchangeRates();
        if(!invalidRates.isEmpty()) {
            for(Currency currency : invalidRates) {
                log.info("잘못된 환율이 입력되어 있습니다. : id={}, rate={}", currency.getId(), currency.getExchangeRate());
            }
        }
    }
}
