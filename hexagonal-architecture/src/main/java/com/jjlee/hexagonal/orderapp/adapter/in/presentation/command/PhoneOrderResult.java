package com.jjlee.hexagonal.orderapp.adapter.in.presentation.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class PhoneOrderResult {
    private String orderId;
    private int price;
}