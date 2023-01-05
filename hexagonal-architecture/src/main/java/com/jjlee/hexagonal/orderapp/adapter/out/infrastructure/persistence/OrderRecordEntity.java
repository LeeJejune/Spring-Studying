package com.jjlee.hexagonal.orderapp.adapter.out.infrastructure.persistence;

import com.jjlee.hexagonal.orderapp.application.order.port.out.command.OrderRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orderRecord")
class OrderRecordEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String orderId;
    private int money;

    private OrderRecordEntity(String orderId, int money) {
        this.orderId = orderId;
        this.money = money;
    }

    public static OrderRecordEntity from(OrderRecord orderRecord) {
        return new OrderRecordEntity(orderRecord.getOrderId(), orderRecord.getMoney());
    }
}