package org.gdg.zipte_gdg.api.controller.admin.shopping;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.admin.shopping.request.CategoryRequest;
import org.gdg.zipte_gdg.api.controller.admin.shopping.request.CategorySetRequest;
import org.gdg.zipte_gdg.api.controller.admin.shopping.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.category.CategoryService;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryResponse;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.CategorySetService;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.response.CategorySetResponse;
import org.gdg.zipte_gdg.api.service.shopping.product.ProductService;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.ProductMangerService;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {

    private final ProductMangerService productMangerService;
    private final CategorySetService categorySetService;
    private final ProductService productService;
    private final CategoryService categoryService;

    // 상품 추가
    @PostMapping
    public ApiResponse<CategorySetResponse> create(CategorySetRequest categorySetRequest) {
        return ApiResponse.created(categorySetService.create(categorySetRequest));
    }

    // 상품 자체 목록 조회
    @GetMapping("/list")
    public ApiResponse<PageResponseDto<ProductResponse>> getList(PageRequestDto pageRequestDto) {
        return ApiResponse.created(productService.findAll(pageRequestDto));
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> get(@PathVariable Long productId) {
        return ApiResponse.created(productService.findById(productId));
    }

    // 카테고리 추가하기
    @PostMapping("/category")
    public ApiResponse<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.created(categoryService.save(categoryRequest));
    }

    // 카테고리에 맞는 상품 자체 조회
    @GetMapping("/category/{id}")
    public ApiResponse<PageResponseDto<ProductResponse>> getProductCategory(@PathVariable("id") Long id, PageRequestDto pageRequestDto) {
        return ApiResponse.created(categorySetService.findAllAdmin(id,pageRequestDto));
    }

    // 상품 매니저 추가
    @PostMapping("/manager")
    public ApiResponse<ProductManagerResponse> create(@RequestBody ProductManagerRequest productManagerRequest) {
        return ApiResponse.created(productMangerService.create(productManagerRequest));
    }

}
