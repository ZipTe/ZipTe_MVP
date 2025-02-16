package com.zipte.platform.adapter.in.api;

import com.zipte.platform.application.port.in.comment.CreateCommentUseCase;
import com.zipte.platform.application.port.in.comment.RemoveCommentUseCase;
import com.zipte.platform.application.port.in.dto.request.board.CommentRequest;
import com.zipte.platform.application.port.out.comment.LoadCommentPort;
import com.zipte.platform.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.zipte.core.common.ApiResponse;
import com.zipte.platform.adapter.in.api.dto.response.CommentResponse;
import com.zipte.core.common.page.request.PageRequest;
import com.zipte.core.common.page.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
@Log4j2
public class CommentController {

    private final CreateCommentUseCase createService;
    private final LoadCommentPort loadService;
    private final RemoveCommentUseCase removeService;

    // 댓글 작성하기
    @PostMapping
    ApiResponse<CommentResponse> createOne(@RequestBody CommentRequest commentRequest) {
        return ApiResponse.created(CommentResponse.from(createService.createComment(commentRequest), commentRequest.getMemberId()));
    }

    @GetMapping("/{boardId}")
    ApiResponse<PageResponse<CommentResponse>> read(
            @PathVariable("boardId") Long boardId,
            PageRequest pageRequest,
            @RequestHeader(value = "userId", required = false) Long userId) {

        // 로그인하지 않은 경우 기본값 설정
        if (userId == null) {
            log.info("익명 사용자가 댓글을 조회함");
            userId = 0L;  // 예를 들어 0L을 익명 사용자 ID로 사용
        }

        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());

        Page<Comment> result = loadService.loadCommentsByBoardId(boardId, pageable);

        List<CommentResponse> dtolist = CommentResponse.from(result.getContent(), userId);

        return ApiResponse.created(new PageResponse<>(dtolist, pageRequest, result.getTotalElements()));
    }



    // 댓글 삭제하기
}
