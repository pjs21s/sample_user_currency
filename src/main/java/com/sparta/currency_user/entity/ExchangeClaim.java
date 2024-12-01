package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter

public class ExchangeClaim extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "amount_in_krw", nullable = false)
    private BigDecimal amountInKrw;

    @Column(name = "amount_after_exchange", nullable = false)
    private BigDecimal amountAfterExchange;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public ExchangeClaimStatus status;

    public ExchangeClaim(User user, Currency currency, BigDecimal amountInKrw, BigDecimal amountAfterExchange, ExchangeClaimStatus status) {
        this.user = user;
        this.currency = currency;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public ExchangeClaim() {
    }

    public void updateStatus(){
        this.status = ExchangeClaimStatus.CANCEL;
    }
}
