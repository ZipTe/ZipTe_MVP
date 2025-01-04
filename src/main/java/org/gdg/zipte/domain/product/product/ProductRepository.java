package org.gdg.zipte.domain.product.product;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select pi from ProductImage pi join Product p on pi.product.id = :productId")
    List<ProductImage> selectProductImages(@Param("productId") Long productId);

    @Query("SELECT p, pi FROM Product p LEFT JOIN ProductImage pi ON p.id = pi.product.id WHERE pi.ord = 0 OR pi.ord IS NULL")
    Page<Object[]> selectList(Pageable pageable);



}
