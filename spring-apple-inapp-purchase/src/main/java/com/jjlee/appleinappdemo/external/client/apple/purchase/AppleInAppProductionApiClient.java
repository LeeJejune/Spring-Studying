package com.jjlee.appleinappdemo.external.client.apple.purchase;

import com.jjlee.appleinappdemo.external.client.apple.purchase.dto.request.AppleReceiptDto;
import com.jjlee.appleinappdemo.external.client.apple.purchase.dto.response.AppStoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AppleInAppProductionApiClient", url = "https://buy.itunes.apple.com")
public interface AppleInAppProductionApiClient {

    @PostMapping("/verifyReceipt")
    AppStoreResponse validateReceiptProduction(@RequestBody AppleReceiptDto userReceipt);
}
