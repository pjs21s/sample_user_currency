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
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
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

        Currency findCurrency = currencyRepository.findById(exchangeRequestDto.getTo_currency_id()).orElseThrow(EntityNotFoundException::new);
        BigDecimal amountAfterExchange = exchangeRequestDto.getAmount_in_krw().divide(findCurrency.getExchangeRate(), 2);
        User findUser = userRepository.findById(exchangeRequestDto.getUser_id()).orElseThrow(EntityNotFoundException::new);

        Exchange result = new Exchange(findUser, findCurrency, exchangeRequestDto.getAmount_in_krw(), amountAfterExchange, findCurrency.getExchangeRate());

        Exchange savedExchange = exchangeRepository.save(result);
        return new ExchangeResponseDto(savedExchange);
    }

    //특정 사용자의 환전 데이터 조회
    @Transactional
    public List<ExchangeResponseDto> getExchangeById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));

        List<Exchange> exchanges = exchangeRepository.findAllByUserId(user);

        if (exchanges.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found");
        }

        return exchanges.stream()
                .map(ExchangeResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public ExchangeResponseDto update(ExchangeRequestDto exchangeRequestDto, Long id) {
        Exchange exchange = exchangeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        exchange.setStatus(exchangeRequestDto.getStatus());

        return new ExchangeResponseDto(exchange);
    }

}
