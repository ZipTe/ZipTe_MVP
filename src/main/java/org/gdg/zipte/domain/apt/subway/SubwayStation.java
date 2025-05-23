package org.gdg.zipte.domain.apt.subway;

import jakarta.persistence.*;
import org.gdg.zipte.domain.apt.own.SubwayOwn;

@Entity
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private SubwayLine subwayLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "own_id")
    private SubwayOwn subwayOwn;

}
