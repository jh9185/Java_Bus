package com.example.java_bus.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="bus_station")
public class BusStationVo {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long busRouteId;
    private String arrmsg;
    private String arrmsg2;
    private String plainNo1;
    private String plainNo2;
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
    private String transYn;
    private Long term;
    private Double posX;
    private Double posY;
}