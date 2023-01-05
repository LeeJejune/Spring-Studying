package com.jjlee.hexagonal.orderapp.application.order.service;

import com.jjlee.hexagonal.orderapp.application.order.port.in.GetReceiptUseCase;
import com.jjlee.hexagonal.orderapp.application.order.port.in.ReceiptResult;
import com.jjlee.hexagonal.orderapp.application.order.port.out.GetOrderRecordPort;
import com.jjlee.hexagonal.orderapp.application.order.port.out.command.OrderRecord;
import com.jjlee.hexagonal.orderapp.domain.order.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetReceiptService implements GetReceiptUseCase {
    private final GetOrderRecordPort orderRecordPort;
    @Override
    public ReceiptResult getReceipt(String orderId) {
        OrderRecord orderRecord = orderRecordPort.getOrder(orderId);
        Receipt receipt = new Receipt(orderRecord.getOrderId(), orderRecord.getMoney());

        return new ReceiptResult(receipt.getOrderId(), receipt.getMoney());
    }
}