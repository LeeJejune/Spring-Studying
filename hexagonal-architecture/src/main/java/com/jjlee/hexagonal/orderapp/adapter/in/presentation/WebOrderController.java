package com.jjlee.hexagonal.orderapp.adapter.in.presentation;

import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderRequest;
import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderResult;
import com.jjlee.hexagonal.orderapp.application.order.port.in.PlaceOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebOrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    @GetMapping("/web/{money}")
    public OrderResult order(@PathVariable int money) {

        OrderRequest orderRequest = new OrderRequest(money);
        return placeOrderUseCase.placeOrder(orderRequest);
    }
}