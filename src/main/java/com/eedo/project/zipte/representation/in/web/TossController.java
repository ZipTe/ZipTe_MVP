package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.zipte.application.port.in.PaymentService;
import com.eedo.project.zipte.application.port.out.api.PaymentApis;
import jakarta.servlet.http.HttpSession;
import com.eedo.project.zipte.representation.request.toss.ConfirmPaymentRequest;
import com.eedo.project.zipte.representation.request.toss.SaveAmountRequest;
import com.eedo.project.zipte.infrastructure.out.pg.toss.response.TossPaymentErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.http.HttpResponse;

@Log4j2
@RestController
@RequestMapping("api/payments/toss")
@RequiredArgsConstructor
public class TossController {

    private final PaymentApis paymentApis;
    private final PaymentService paymentService;

    // 임시저장하기
    @PostMapping("/saveAmount")
    public ResponseEntity<?> tempsave(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {
        session.setAttribute(saveAmountRequest.getOrderId(), saveAmountRequest.getAmount());
        return ResponseEntity.ok("Payment temp save successful");
    }


    // 임시저장한 내용과 동일한지 파악
    @PostMapping("/verifyAmount")
    public ResponseEntity<?> verifyAmount(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {

        String amount = (String) session.getAttribute(saveAmountRequest.getOrderId());
        // 결제 전의 금액과 결제 후의 금액이 같은지 검증
        if (amount == null || !amount.equals(saveAmountRequest.getAmount()))
            return ResponseEntity.badRequest().body(TossPaymentErrorResponse.builder().code(400).message("결제 금액 정보가 유효하지 않습니다.").build());

        // 검증에 사용했던 세션은 삭제
        session.removeAttribute(saveAmountRequest.getOrderId());

        return ResponseEntity.ok("Payment is valid");
    }

    // 리액트에서 결제한 내용 얻기
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPayment(@RequestBody ConfirmPaymentRequest confirmPaymentRequest) throws Exception {

        log.info("Confirm payment request:" + confirmPaymentRequest);

        // 토스에게 결제 승인 요청
        HttpResponse<String> response = paymentApis.requestConfirm(confirmPaymentRequest);

        // 응답 코드 확인
        if (response.statusCode() == 200) {
            // 결제 정보 데이터베이스 저장 시도
            paymentService.savePayment(confirmPaymentRequest, response);
            return ResponseEntity.ok(response.body()); // 성공 시 결제 객체 반환
        } else {
            // 결제가 승인되지 않음
            log.info("결제 승인 실패: " + response.body().toString());
            return ResponseEntity.status(response.statusCode())
                    .body("결제 승인 실패: " + response.body());
        }
    }

    /**
     * 결제 취소 요청
     */
//    public HttpResponse requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException {
//        System.out.println(paymentKey);
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("<https://api.tosspayments.com/v1/payments/>" + paymentKey + "/cancel"))
//                .header("Authorization", authorizations)
//                .header("Content-Type", "application/json")
//                .method("POST", HttpRequest.BodyPublishers.ofString("{\\"cancelReason\\":\\"" + cancelReason + "\\"}"))
//                .build();
//        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//    }


}
