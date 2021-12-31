package com.example.java_bus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusStation {
    private Long number;
    private Long busRouteId;
    private String busRouteNm;
    private String corpNm;
    private String edStationNm;
    private Long firstBusTm;
    private Long firstLowTm;
    private Long lastBusTm;
    private Long lastBusYn;
    private Long lastLowTm;
    private Long length;
    private Long routeType;
    private String stStationNm;
    private Long term;
    private Double posX;
    private Double posY;
}

