package com.example.java_bus.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    private Long boardId;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createData;
    private int read;
    private Long memberId;
}
