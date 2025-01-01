package org.gdg.zipte_gdg.api.controller.shopping.categorySet;


import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.DiscountProductResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.CategorySetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category/product")
public class CategorySetController {

    private final CategorySetService categorySetService;

    @GetMapping("admin/{id}")
    public ApiResponse<PageResponseDto<ProductResponse>> getProductCategory(@PathVariable("id") Long id, PageRequestDto pageRequestDto) {
        return ApiResponse.created(categorySetService.findAllAdmin(id,pageRequestDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<PageResponseDto<DiscountProductResponse>> getDiscountProductAll(@PathVariable("id") Long id, PageRequestDto pageRequestDto) {
        return ApiResponse.created(categorySetService.findAll(id,pageRequestDto));
    }
}
