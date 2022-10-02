package com.jjlee.appleinappdemo.service.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jjlee.appleinappdemo.external.client.apple.purchase.dto.request.AppleReceiptDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserReceiptDto {
    @JsonProperty("receipt-data")
    private String receiptData;

    public static UserReceiptDto of(@JsonProperty("receipt-data") String receiptData) {
        return new UserReceiptDto(receiptData);
    }

    public AppleReceiptDto toClientDto(){
        return AppleReceiptDto.of(receiptData);
    }
}
