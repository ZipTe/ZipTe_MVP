package org.gdg.zipte_gdg.api.controller.apartment.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.controller.apartment.review.request.ReviewRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.apartment.review.ReviewService;
import org.gdg.zipte_gdg.api.service.apartment.review.response.ReviewResponseDto;
import org.gdg.zipte_gdg.api.service.apartment.review.response.ReviewResponseWithCommentDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/review")
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponseDto> create(ReviewRequestDto reviewRequestDto) {
        return ApiResponse.created(reviewService.register(reviewRequestDto));
    }

    @GetMapping("/list/member/{memberId}")
    public ApiResponse<PageResponseDto<ReviewResponseDto>> getListById(@PathVariable("memberId") Long memberId, PageRequestDto pageRequestDto) {
        return ApiResponse.created(reviewService.getReviewsByMemberId(pageRequestDto, memberId));
    }

    @GetMapping("/list/apt/{aptId}")
    public ApiResponse<PageResponseDto<ReviewResponseDto>> getListByAptId(
            @PathVariable("aptId") Long aptId,
            PageRequestDto pageRequestDto,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy) {

        switch (orderBy) {
            case "view":
                return ApiResponse.created(reviewService.getListByAptIdOrderByCountView(pageRequestDto, aptId));
            case "rating":
                return ApiResponse.created(reviewService.getListByAptIdOrderByRating(pageRequestDto, aptId));
            case "date":
            default:
                return ApiResponse.created(reviewService.getListByAptId(pageRequestDto, aptId));
        }
    }

    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResponseWithCommentDto> getReviewsWithComments(@PathVariable("reviewId") Long reviewId) {
        log.info("Logs" + reviewId);
        return ApiResponse.created(reviewService.getOne(reviewId));
    }

    // ----------필요없는 것 --------
    //    @GetMapping("/list")
//    public ApiResponse<PageResponseDto<ReviewResponseDto>> getList(PageRequestDto pageRequestDto) {
//        return ApiResponse.created(reviewService.getList(pageRequestDto));
//    }


}
