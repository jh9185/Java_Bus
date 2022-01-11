package com.example.java_bus.service;

import com.example.java_bus.vo.BusStationVo;

import java.util.List;
import java.util.Optional;

public interface BusService {
    BusStationVo save(BusStationVo board);
    Optional<BusStationVo> findById(Long busNo);
    List<BusStationVo> findAll();
    void deleteById(Long busNo);
    void updateById(Long busNo, BusStationVo busInfo);

}
