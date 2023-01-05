package com.jjlee.hexagonal.orderapp.adapter.out.infrastructure.persistence;

import com.jjlee.hexagonal.orderapp.application.order.port.out.GetOrderRecordPort;
import com.jjlee.hexagonal.orderapp.application.order.port.out.command.OrderRecord;
import com.jjlee.hexagonal.orderapp.application.order.port.out.RecordOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecordOrderAdapter implements RecordOrderPort, GetOrderRecordPort {
    private final OrderRecordRepository orderRecordRepository;

    @Override
    public void recordOrder(OrderRecord orderRecord) {
        orderRecordRepository.save(OrderRecordEntity.from(orderRecord));
    }

    @Override
    public OrderRecord getOrder(String orderId) {
        OrderRecordEntity orderRecord = orderRecordRepository.findByOrderId(orderId).orElseThrow(IllegalAccessError::new);

        return new OrderRecord(orderRecord.getOrderId(), orderRecord.getMoney());
    }
}
