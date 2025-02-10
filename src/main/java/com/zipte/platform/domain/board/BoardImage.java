package com.zipte.platform.domain.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rimage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Board board;

    private String fileName;

    private int ord;


    public static BoardImage of(Board board, String fileName) {
        return BoardImage.builder()
                .board(board)
                .fileName(fileName)
                .ord(0)
                .build();
    }


    //로직
    public void setReview(Board board) {
        this.board = board;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }
}
