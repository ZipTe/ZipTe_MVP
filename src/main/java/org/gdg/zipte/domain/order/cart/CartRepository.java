package org.gdg.zipte.domain.order.cart;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 멤버별 장바구니 가져오기
    Optional<Cart> findByMemberId(Long memberId);

    // 장바구니 내 아이템 가격 가겨오기


}
