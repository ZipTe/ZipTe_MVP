package org.gdg.zipte.api.service.order.toss;

import org.gdg.zipte.api.controller.order.toss.request.ConfirmPaymentRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;

@Service
public interface TossService {

    // 결제 검증 요청
    HttpResponse<String> requestConfirm(ConfirmPaymentRequest confirmPaymentRequest) throws IOException, InterruptedException;

    // 결제 취소 요청
    HttpResponse<String> requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException;
}
