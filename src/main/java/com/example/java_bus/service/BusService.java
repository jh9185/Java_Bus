package com.example.java_bus.service;

import com.example.java_bus.vo.BusNumberVo;

import java.util.List;
import java.util.Optional;

public interface BusService {
    BusNumberVo save(BusNumberVo busNumber);
    List<BusNumberVo> saveAll(List<BusNumberVo> busNumberVos);
    Optional<BusNumberVo> findByrouteId(Long busrouteId);
    Optional<BusNumberVo> findByName(String name);
    List<BusNumberVo> findAll();
    void deleteAll();
    void deleteById(Long busNo);
    void updateById(Long busNo, BusNumberVo busInfo);
    void updateByrouteId(Long busrouteId, BusNumberVo busInfo);

    void resetId();

}
