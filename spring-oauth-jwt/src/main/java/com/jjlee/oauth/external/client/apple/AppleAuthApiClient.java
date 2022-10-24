package com.jjlee.oauth.external.client.apple;

import com.jjlee.oauth.external.client.apple.dto.response.ApplePublicKeyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "appleAuthApiClient", url = "https://appleid.apple.com/auth")
public interface AppleAuthApiClient {
    @GetMapping(value = "/keys")
    ApplePublicKeyResponse getAppleAuthPublicKey();
}
