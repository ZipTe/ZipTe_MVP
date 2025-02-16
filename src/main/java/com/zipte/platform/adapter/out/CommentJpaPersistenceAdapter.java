package com.zipte.platform.adapter.out;

import com.zipte.platform.adapter.out.jpa.comment.CommentJpaEntity;
import com.zipte.platform.adapter.out.jpa.comment.CommentJpaRepository;
import com.zipte.platform.application.port.out.comment.LoadCommentPort;
import com.zipte.platform.application.port.out.comment.RemoveCommentPort;
import com.zipte.platform.application.port.out.comment.SaveCommentPort;
import com.zipte.platform.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentJpaPersistenceAdapter implements LoadCommentPort, SaveCommentPort, RemoveCommentPort {

    private final CommentJpaRepository repository;

    @Override
    public Comment saveComment(Comment comment) {

        var entity = CommentJpaEntity.from(comment);

        return CommentJpaEntity.toDomain(repository.save(entity));

    }

    @Override
    public Optional<Comment> loadCommentById(Long id) {

        return repository.findById(id)
                .map(CommentJpaEntity::toDomain);
    }

    @Override
    public Page<Comment> loadCommentsByBoardId(Long boardId, Pageable pageable) {
        Page<CommentJpaEntity> result = repository.findRootCommentsByBoardId(boardId, pageable);

        List<Comment> comments = result.stream()
                .map(CommentJpaEntity::toDomain)
                .toList();

        return new PageImpl<>(comments, pageable, result.getTotalElements());
    }

    @Override
    public void removeComment(Long id) {
        repository.deleteById(id);
    }


}
