package com.jjlee.hexagonal.orderapp.application.order.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRequest {
    private int money;
}
