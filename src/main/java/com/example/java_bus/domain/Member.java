package com.example.java_bus.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {
    private Long membernumber;
    private String id;
    private String password;
}