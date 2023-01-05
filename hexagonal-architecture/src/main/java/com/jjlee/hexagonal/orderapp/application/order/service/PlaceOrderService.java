package com.jjlee.hexagonal.orderapp.application.order.service;

import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderRequest;
import com.jjlee.hexagonal.orderapp.application.order.port.in.command.OrderResult;
import com.jjlee.hexagonal.orderapp.application.order.port.in.PlaceOrderUseCase;
import com.jjlee.hexagonal.orderapp.application.order.port.out.command.OrderRecord;
import com.jjlee.hexagonal.orderapp.application.order.port.out.RecordOrderPort;
import com.jjlee.hexagonal.orderapp.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {
    private final RecordOrderPort recordOrderPort;

    @Transactional
    @Override
    public OrderResult placeOrder(OrderRequest orderDetail) {
        Order order = new Order(UUID.randomUUID().toString(), orderDetail.getMoney());
        order.place();

        recordOrderPort.recordOrder(new OrderRecord(order.getId(), order.getMoney()));

        return new OrderResult(order.getId(), order.getMoney());
    }
}