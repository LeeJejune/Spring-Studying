package jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemDto {
    private int Price;
    private String name;
    private int stockQuantity;
}
