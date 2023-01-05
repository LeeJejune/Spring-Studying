package com.jjlee.hexagonal.orderapp.adapter.in.presentation;

import com.jjlee.hexagonal.orderapp.adapter.in.presentation.command.PhoneOrderResult;
import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderRequest;
import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderResult;
import com.jjlee.hexagonal.orderapp.application.order.port.in.PlaceOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PhoneOrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    @GetMapping("/phone/{money}")
    public PhoneOrderResult order(@PathVariable int money) {
        OrderRequest orderRequest = new OrderRequest(money);
        OrderResult orderResult=  placeOrderUseCase.placeOrder(orderRequest);

        return new PhoneOrderResult(orderResult.getOrderId(), orderResult.getMoney());
    }
}