package com.jjlee.appleinappdemo.controller.payment;


import com.jjlee.appleinappdemo.controller.payment.dto.request.UserPurchaseReceipt;
import com.jjlee.appleinappdemo.service.payment.PaymentService;
import com.jjlee.appleinappdemo.service.payment.dto.response.InAppPurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/paymeny")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/verify")
    public InAppPurchaseResponse validatePurchase(@RequestBody UserPurchaseReceipt userReceipt){
        return paymentService.purchaseValidate(userReceipt.toServiceDto());
    }
}
