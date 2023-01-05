package com.jjlee.hexagonal.orderapp.application.order.port.in;

import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderRequest;
import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderResult;

public interface PlaceOrderUseCase {
    OrderResult placeOrder(OrderRequest orderDetail);
}
