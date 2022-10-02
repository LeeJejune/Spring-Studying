package com.jjlee.appleinappdemo.service.payment.impl;

import com.jjlee.appleinappdemo.domain.Payment;
import com.jjlee.appleinappdemo.domain.PaymentRepository;
import com.jjlee.appleinappdemo.external.client.apple.purchase.dto.response.AppStoreResponse;
import com.jjlee.appleinappdemo.external.client.apple.purchase.dto.response.InApp;
import com.jjlee.appleinappdemo.service.payment.PaymentService;
import com.jjlee.appleinappdemo.service.payment.dto.request.UserReceiptDto;
import com.jjlee.appleinappdemo.service.payment.dto.response.InAppPurchaseResponse;
import com.jjlee.appleinappdemo.service.payment.util.AppleInAppPurchaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ApplePaymentService implements PaymentService {

    private final AppleInAppPurchaseValidator appleInAppPurchaseValidator;
    private final PaymentRepository paymentRepository;
    @Override
    public InAppPurchaseResponse purchaseValidate(UserReceiptDto userReceipt){
        final AppStoreResponse response = appleInAppPurchaseValidator.appleInAppPurchaseVerify(userReceipt)
                .orElseThrow(() -> new IllegalArgumentException("검증 되지 않은 응답입니다."));

        final String transactionId = getTransactionId(response);

        final Payment payment = findOrCreatePayment(transactionId, response.getStatus());
        paymentRepository.save(payment);
        return InAppPurchaseResponse.of(payment.getPaymentState(), payment.getTransactionId());
    }

    private Payment findOrCreatePayment(final String transactionId, final int paymentState) {
        return paymentRepository.findByTransactionId(transactionId)
                .orElseGet(() -> Payment.of(transactionId, paymentState));
    }
    private String getTransactionId(final AppStoreResponse response){
        final List<InApp> inAppData = response.getReceipt().getInApp();

        final InApp app = inAppData.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("트랜잭션 Id가 없습니다."));
        return app.getTransactionId();
    }
}
