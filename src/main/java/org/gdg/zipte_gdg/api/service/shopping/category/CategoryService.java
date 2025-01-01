package org.gdg.zipte_gdg.api.service.shopping.category;

import org.gdg.zipte_gdg.api.controller.shopping.category.request.CategoryRequest;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryResponse;

import java.util.*;

public interface CategoryService {

    // 새로운 카테고리를 추가
    CategoryResponse save(CategoryRequest categoryRequest);

    // 전체 카테고리 보기
    List<CategoryResponse> findAll();

    // 카테고리 아이디로 조회
    CategoryResponse getCategory(Long categoryId);

}
