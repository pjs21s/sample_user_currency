package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy="userId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Exchange> exchanges;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}
}