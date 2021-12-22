package com.example.java_bus.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Login {
    private Long LoginId;
    private String password;
}
