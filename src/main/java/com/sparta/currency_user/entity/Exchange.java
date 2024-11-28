package com.sparta.currency_user.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@Table(name = "exchange")
public class Exchange extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "amount_in_krw")
    private BigDecimal amountInKrw;

    @Column(name = "amount_after_exchange")
    private String amountAfterExchange;

    private String status;

    public Exchange(){}

    public Exchange(BigDecimal amountInKrw , String status ){

        this.amountInKrw = amountInKrw;
        this.status =status;
    }
    public void updateStatus(String status){
        this.status = status;
    }

    public void usdExchange(BigDecimal exchangeRate , String symbol){
        this.amountAfterExchange = amountInKrw.divide(exchangeRate,2 , RoundingMode.HALF_EVEN) + symbol;
    }

    public void jpyExchange(BigDecimal exchangeRate , String symbol){
        this.amountAfterExchange = amountInKrw.divide(exchangeRate,0 , RoundingMode.HALF_EVEN) + symbol;
    }

}
