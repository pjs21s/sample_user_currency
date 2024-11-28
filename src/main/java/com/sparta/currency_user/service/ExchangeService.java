package com.sparta.currency_user.service;


import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final UserRepository userRepository;
    private final ExchangeRepository exchangeRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public ExchangeResponseDto save(ExchangeRequestDto exchangeRequestDto) {

        //환전 할 통화
        Currency findCurrency = currencyRepository.findById(exchangeRequestDto.getToCurrencyId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 통화가 없습니다."));

        //환전 후 값
        BigDecimal amountAfterExchange = exchangeRequestDto.getAmountInKrw().divide(findCurrency.getExchangeRate(), 2);

        //요청한 사용자
        User findUser = userRepository.findById(exchangeRequestDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 사용자가 없습니다."));

        Exchange result = new Exchange(findUser, findCurrency, exchangeRequestDto.getAmountInKrw(), amountAfterExchange, findCurrency.getExchangeRate());

        Exchange savedExchange = exchangeRepository.save(result);
        return new ExchangeResponseDto(savedExchange);
    }

    //특정 사용자의 환전 데이터 조회
    @Transactional
    public List<ExchangeResponseDto> getExchangeById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 사용자를 찾을 수 없습니다."));

        List<Exchange> exchanges = exchangeRepository.findAllByUserId(user);

        return exchanges.stream()
                .map(ExchangeResponseDto::new)
                .collect(Collectors.toList());

    }

    //특정 환전 건, 상태값 update
    @Transactional
    public ExchangeResponseDto update(ExchangeRequestDto exchangeRequestDto, Long id) {
        Exchange exchange = exchangeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 환전 건을 찾을 수 없습니다."));

        exchange.setStatus(exchangeRequestDto.getStatus());

        return new ExchangeResponseDto(exchange);
    }


}
