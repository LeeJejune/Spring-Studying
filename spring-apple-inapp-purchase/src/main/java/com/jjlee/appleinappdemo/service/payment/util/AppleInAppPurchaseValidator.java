package com.jjlee.appleinappdemo.service.payment.util;

import com.jjlee.appleinappdemo.external.client.apple.purchase.AppleInAppProductionApiClient;
import com.jjlee.appleinappdemo.external.client.apple.purchase.AppleInAppSandboxApiClient;
import com.jjlee.appleinappdemo.external.client.apple.purchase.dto.response.AppStoreResponse;
import com.jjlee.appleinappdemo.service.payment.dto.request.UserReceiptDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppleInAppPurchaseValidator {
    private static final int ACCOUNT_ERROR = 21010;
    private static final int RECEIPT_ERROR = 21002;
    private static final int SANDBOX_TEST = 21007;
    private final AppleInAppProductionApiClient productionApiClient;
    private final AppleInAppSandboxApiClient sandboxApiClient;
    public Optional<AppStoreResponse> appleInAppPurchaseVerify(final UserReceiptDto receiptRequest){
        final AppStoreResponse appStoreResponse = productionApiClient.validateReceiptProduction(receiptRequest.toClientDto());
        switch (appStoreResponse.getStatus()){
            case RECEIPT_ERROR:
                throw new IllegalArgumentException("receipt-data가 잘못되었습니다. 정확한 receipt-date를 다시 보내주세요 " + RECEIPT_ERROR);
            case ACCOUNT_ERROR:
                throw new IllegalArgumentException("만료되었거나 삭제된 계정입니다. " + ACCOUNT_ERROR);
            case SANDBOX_TEST:
                return Optional.of(sandboxApiClient.validateReceiptSandbox(receiptRequest.toClientDto()));
        }
        return Optional.of(appStoreResponse);
    }
}
