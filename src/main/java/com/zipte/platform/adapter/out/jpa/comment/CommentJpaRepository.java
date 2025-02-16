package com.zipte.platform.adapter.out.jpa.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentJpaRepository extends JpaRepository<CommentJpaEntity, Long> {

    @Query("SELECT c FROM CommentJpaEntity c WHERE c.board.id = :boardId AND c.parent IS NULL")
    Page<CommentJpaEntity> findRootCommentsByBoardId(@Param("boardId") Long boardId,  Pageable pageable);

}
