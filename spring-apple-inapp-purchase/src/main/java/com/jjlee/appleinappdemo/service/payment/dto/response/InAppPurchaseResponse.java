package com.jjlee.appleinappdemo.service.payment.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InAppPurchaseResponse {
    private final int status;
    private final String transactionId;

    private InAppPurchaseResponse(final int status, final String transactionId) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return this.status == 21007;
    }

    public static InAppPurchaseResponse of(final int status, final String transactionId){
        InAppPurchaseResponse response = new InAppPurchaseResponse(
                status,
                transactionId
        );
        return response;
    }
}
