package com.jjlee.hexagonal.orderapp.application.order.port.out.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRecord {
    private String orderId;
    private int money;
}