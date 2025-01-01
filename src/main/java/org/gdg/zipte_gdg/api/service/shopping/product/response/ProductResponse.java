package org.gdg.zipte_gdg.api.service.shopping.product.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.gdg.zipte_gdg.domain.shopping.product.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
public class ProductResponse {

    private Long id;
    private String pname;
    private String pdesc;
    private int price;
    private int stock;
    
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();


    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .pname(product.getPname())
                .stock(product.getStock())
                .price(product.getPrice())
                .pdesc(product.getPdesc())
                .build();
    }
}
