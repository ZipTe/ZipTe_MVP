package org.gdg.zipte_gdg.domain.Toss;


import jakarta.persistence.*;
import lombok.*;
import org.gdg.zipte_gdg.domain.order.Order;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false, unique = true)
    private String tossPaymentKey;

    // 토스내부에서 관리하는 별도의 orderId가 존재함
    @Column(nullable = false)
    private String tossOrderId;
//
//    @OneToOne
//    @JoinColumn(name = "order_id", nullable = false)
//    private Order order;

    private int totalAmount;

    @Enumerated(value = EnumType.STRING)
//    @Column(nullable = false)
    private TossPaymentMethod tossPaymentMethod;

    @Enumerated(value = EnumType.STRING)
//    @Column(nullable = false)
    private TossPaymentStatus tossPaymentStatus;

//    @Column(nullable = false)
    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    public static TossPayment createPayment(String tossPaymentKey, String tossOrderId, int totalAmount) {
        return TossPayment.builder()
                .tossPaymentKey(tossPaymentKey)
                .tossOrderId(tossOrderId)
                .totalAmount(totalAmount)
                .requestedAt(LocalDateTime.now())
                .build();

    }
}