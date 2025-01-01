package org.gdg.zipte_gdg.api.service.shopping.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartResponse;
import org.gdg.zipte_gdg.domain.shopping.category.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String name;

    @Builder.Default
    private List<CategoryResponse> children = new ArrayList<>();

    // 자식 카테고리를 재귀적으로 처리하여 자식의 자식도 추가
    private void setChildren(List<Category> children) {
        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            children.forEach(category -> {
                CategoryResponse child = CategoryResponse.of(category);
                this.children.add(child);  // 자식 카테고리를 추가
            });
        }
    }


    // 생성자
    public static CategoryResponse of(Category category) {
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        categoryResponse.setChildren(category.getChildren());

        return categoryResponse;

    }

    // 여러개 일때 생성자
    public static List<CategoryResponse> ofs(List<Category> categories) {
        List<CategoryResponse> responseList = new ArrayList<>();
        for (Category category : categories) {
            responseList.add(CategoryResponse.of(category));
        }
        return responseList;
    }
}
