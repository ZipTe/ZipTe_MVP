package com.zipte.platform.adapter.out.jpa.board;

import com.zipte.platform.domain.board.BoardStatistics;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardStatisticsJpaEntity {

    private long viewCount;
    private long commentCount;
    private long likeCount;

    // from
    public static BoardStatisticsJpaEntity from(BoardStatistics boardStatistics) {
        return new BoardStatisticsJpaEntity
                (boardStatistics.getViewCount(), boardStatistics.getCommentCount(), boardStatistics.getLikeCount());
    }


    // toDomain
    public BoardStatistics toDomain(){
        return BoardStatistics.builder()
                .viewCount(this.viewCount)
                .commentCount(this.commentCount)
                .likeCount(this.likeCount)
                .build();

    }

}
