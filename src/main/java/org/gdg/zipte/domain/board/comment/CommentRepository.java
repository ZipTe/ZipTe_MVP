package org.gdg.zipte.domain.board.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.board.id = :boardId AND c.parent IS NULL")
    Page<Comment> findRootCommentsByBoardId(@Param("boardId") Long boardId,  Pageable pageable);

}
