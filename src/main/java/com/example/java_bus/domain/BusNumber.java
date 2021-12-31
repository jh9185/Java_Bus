package com.example.java_bus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusNumber {
    private Long number;
    private Long busRouteId;
    private String busRouteNm;
}
