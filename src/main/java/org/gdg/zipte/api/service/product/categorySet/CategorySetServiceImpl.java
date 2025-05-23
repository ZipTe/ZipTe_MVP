package org.gdg.zipte.api.service.product.categorySet;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.api.controller.admin.product.request.ProductRequest;
import org.gdg.zipte.api.service.product.category.response.CategoryNoChildrenResponse;
import org.gdg.zipte.api.service.product.productManger.response.DiscountProductResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.api.service.product.product.ProductImageService;
import org.gdg.zipte.api.service.product.product.response.ProductResponse;
import org.gdg.zipte.api.service.product.categorySet.response.CategorySetResponse;
import org.gdg.zipte.domain.product.category.Category;
import org.gdg.zipte.domain.product.category.CategoryRepository;
import org.gdg.zipte.domain.product.product.Product;
import org.gdg.zipte.domain.product.product.ProductImage;
import org.gdg.zipte.domain.product.product.ProductRepository;
import org.gdg.zipte.domain.product.categorySet.CategorySet;
import org.gdg.zipte.domain.product.categorySet.CategorySetRepository;
import org.gdg.zipte.domain.product.productManger.ProductManager;
import org.gdg.zipte.domain.product.productManger.ProductManagerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CategorySetServiceImpl implements CategorySetService {

    private final CategoryRepository categoryRepository;
    private final CategorySetRepository categorySetRepository;
    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final ProductManagerRepository productManagerRepository;

    @Override
    public CategorySetResponse create(ProductRequest request) {

        // 상품 생성
        Product newProduct = Product.of(request.getPname(),
                request.getPdesc(), request.getPrice(), request.getStock());
        Product product = productRepository.save(newProduct);

        //상품 매니저 저장
        ProductManager newProductManager = ProductManager.of(product, 0, true,"basic");
        productManagerRepository.save(newProductManager);

        // 상품 사진
        List<String> uploads = productImageService.saveFiles(product, request.getFiles());

        // 현재 존재하는 카테고리 조회
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();

        CategorySet categorySet = CategorySet.of(product, category);
        CategorySet save = categorySetRepository.save(categorySet);

        CategorySetResponse categorySetResponse = CategorySetResponse.from(save);
        categorySetResponse.getProduct().setUploadFileNames(uploads);

        return categorySetResponse;
    }

    @Override
    public PageResponse<ProductResponse> findAllAdmin(Long id, PageRequest pageRequest) {

        // 1. 카테고리 ID와 자식 카테고리 ID 목록 생성
        List<Long> categoryIds = findAllChildCategoryIds(id);

        // 2. 자식 카테고리를 포함한 ID 목록을 이용해 Product 조회
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());
        Page<Object[]> result = categorySetRepository.findProductCategoriesByIds(categoryIds, pageable);

        // 3. 결과 리스트 변환
        List<ProductResponse> dtoList = result.get().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            String imageStr = (productImage != null) ? productImage.getFileName() : "No image found";
            ProductResponse dto = ProductResponse.from(product);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponse<>(dtoList, pageRequest, total);
    }

    private List<Long> findAllChildCategoryIds(Long id) {
        List<Long> result = new ArrayList<>();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));

        result.add(category.getId()); // 현재 카테고리 ID 추가
        for (Category child : category.getChildren()) {
            result.addAll(findAllChildCategoryIds(child.getId())); // 재귀 호출로 자식 카테고리 ID 추가
        }
        return result;
    }

    @Override
    public PageResponse<DiscountProductResponse> findAll(Long id, PageRequest pageRequest) {
        // 1. 카테고리 ID와 자식 카테고리 ID 목록 생성
        List<Long> categoryIds = findAllChildCategoryIds(id);

        // 2. 자식 카테고리를 포함한 ID 목록을 이용해 Product 조회
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());
        Page<Object[]> result = categorySetRepository.findProductManagerCategoriesByIds(categoryIds, pageable);

        // 3. 결과 리스트 변환
        List<DiscountProductResponse> dtoList = result.get().map(arr -> {
            ProductManager productManager = (ProductManager) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            // 카테고리 정보 넣기
            CategorySet category = productManagerRepository.findCategoryByProductId(productManager.getProduct().getId());
            CategoryNoChildrenResponse responseNoChildren = CategoryNoChildrenResponse.from(category.getCategory());

            String imageStr = (productImage != null) ? productImage.getFileName() : "No image found";
            DiscountProductResponse dto = DiscountProductResponse.from(productManager);
            dto.getProduct().setUploadFileNames((Collections.singletonList(imageStr)));
            dto.setCategory(responseNoChildren);


            return dto;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponse<>(dtoList, pageRequest, total);
    }

}
