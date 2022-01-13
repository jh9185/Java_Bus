package com.example.java_bus.service;

import com.example.java_bus.bus.BusNumber;
import com.example.java_bus.vo.BusNumberVo;
import com.example.java_bus.vo.BusStationVo;

import java.util.List;
import java.util.Optional;

public interface BusService {
    BusNumberVo save(BusNumberVo busNumber);
    List<BusNumberVo> saveAll(List<BusNumberVo> busNumberVos);
    Optional<BusNumberVo> findByrouteId(Long routeId);
    Optional<BusNumberVo> findByName(String name);
    List<BusNumberVo> findAll();
    void deleteAll();
    void deleteById(Long busNo);
    void updateById(Long busNo, BusNumberVo busInfo);
    void updateByrouteId(Long routeId, BusNumberVo busInfo);

    void resetId();

}
