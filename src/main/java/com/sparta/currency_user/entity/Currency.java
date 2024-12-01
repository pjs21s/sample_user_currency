package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(length = 10)
    private String symbol;

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public Currency() {}
}
