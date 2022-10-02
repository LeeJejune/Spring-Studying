package com.jjlee.appleinappdemo.service.payment;

import com.jjlee.appleinappdemo.service.payment.dto.request.UserReceiptDto;
import com.jjlee.appleinappdemo.service.payment.dto.response.InAppPurchaseResponse;

public interface PaymentService {
    InAppPurchaseResponse purchaseValidate(UserReceiptDto userReceiptDto);
}
