package com.example.java_bus.vo;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="board")
public class BoardVo {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String name;
    @Column
    private LocalDateTime regdate;

    @Builder public BoardVo(String title, String content, String name) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.regdate = LocalDateTime.now();
    }
}
