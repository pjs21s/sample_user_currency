package com.sparta.currency_user.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "to_currency_id")
    private Currency currency;

    @Column(name = "amount_in_krw")
    private Long amountInKrw;

    @Column(name = "amount_after_exchange")
    private Float amountAfterExchange;

    private String status;

    public Exchange(){}

    public void updateStatus(String status){
        this.status = status;
    }

}
