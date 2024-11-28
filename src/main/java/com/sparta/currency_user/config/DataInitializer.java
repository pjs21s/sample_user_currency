package com.sparta.currency_user.config;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@Profile("dev")
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        // Tutor 데이터 초기화
        User user1 = new User("user1" , "aa@aa.com");
        User user2 = new User("user2" , "bb@bb.com");

        BigDecimal exchangeRate1 = new BigDecimal(1430);
        Currency currency1 = new Currency("USD" , exchangeRate1 , "$");

        BigDecimal exchangeRate2 = new BigDecimal(-150);
        Currency currency2 = new Currency("JPY" , exchangeRate2 , "円");

        userRepository.save(user1);
        userRepository.save(user2);
        currencyRepository.save(currency1);
        currencyRepository.save(currency2);

        log.info("===== Test Data Initialized =====");
    }

}