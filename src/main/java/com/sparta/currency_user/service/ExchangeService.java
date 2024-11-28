package com.sparta.currency_user.service;


import com.sparta.currency_user.dto.exchange.ExchangeCountResponseDto;
import com.sparta.currency_user.dto.exchange.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.error.errorcode.ErrorCode;
import com.sparta.currency_user.error.exception.CustomException;
import com.sparta.currency_user.repository.ExchangeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserService userService;
    private final CurrencyService currencyService;

    public ExchangeResponseDto save (Long userId , Long currencyId , BigDecimal amountInKrw){
        User findUser  = userService.findUserById(userId);
        Currency findCurrency = currencyService.findCurrencyById(currencyId);

        Exchange exchange = new Exchange(amountInKrw, "normal" , findCurrency.getExchangeRate());
        exchange.setUser(findUser);
        exchange.setCurrency(findCurrency);
        exchangeRepository.save(exchange);

        return new ExchangeResponseDto(exchange.getId() , exchange.getUser().getId() , exchange.getCurrency().getId() , exchange.getAmountInKrw() , exchange.getAmountAfterExchange() , exchange.getStatus());
    }

    public Exchange findExchangeById(Long exchangeId) {
        return exchangeRepository.findById(exchangeId).orElseThrow(() -> new CustomException(ErrorCode.EXCHANGE_NOT_FOUND));
    }

    public ExchangeResponseDto findById (Long exchangeId){
        return new ExchangeResponseDto(findExchangeById(exchangeId));
    }

    @Transactional
    public void cancelledExchange(Long exchangeId){
        Exchange findExchange = findExchangeById(exchangeId);
        findExchange.updateStatus("cancelled");
    }


    public List<ExchangeCountResponseDto> findTotalExchange(Long userId, Long currencyId){
        return exchangeRepository.findTotalExchange(userId,currencyId);
    }









}
