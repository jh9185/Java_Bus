package com.example.java_bus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Busstop {
    private Long number;
    private String name;
    private float x;
    private float y;
}
