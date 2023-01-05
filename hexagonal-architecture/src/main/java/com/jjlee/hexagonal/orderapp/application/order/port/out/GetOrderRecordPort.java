package com.jjlee.hexagonal.orderapp.application.order.port.out;

import com.jjlee.hexagonal.orderapp.application.order.port.out.command.OrderRecord;

public interface GetOrderRecordPort {
    OrderRecord getOrder(String orderId);
}
