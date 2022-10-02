package com.jjlee.appleinappdemo.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String transactionId;
    @NotNull
    private int paymentState;
    private Payment(final Long id, final String transactionId, final int state) {
        this.id = id;
        this.transactionId = transactionId;
        this.paymentState = state;
    }
    public static Payment of(final String transactionId, final int state) {
        return new Payment(null, transactionId, state);
    }
}