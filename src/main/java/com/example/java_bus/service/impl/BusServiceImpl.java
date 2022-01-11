package com.example.java_bus.service.impl;

import com.example.java_bus.bus.BusNumber;
import com.example.java_bus.repository.BusRepository;
import com.example.java_bus.service.BusService;
import com.example.java_bus.vo.BusNumberVo;
import com.example.java_bus.vo.BusStationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    public BusNumberVo save(BusNumberVo busNumber) {
        busRepository.save(busNumber);
        return busNumber;
    }

    public List<BusNumberVo> findAll() {
        List<BusNumberVo> busLists = new ArrayList<>();
        busRepository.findAll().forEach(e -> busLists.add(e));
        return busLists;
    }
    public Optional<BusNumberVo> findById(Long busNo) {
        Optional<BusNumberVo> bus = busRepository.findById(busNo);
        return bus;
    }

    public Optional<BusNumberVo> findByrouteId(Long routeId) {
        Optional<BusNumberVo> bus = busRepository.findByrouteId(routeId);
        return bus;
    }

    public Optional<BusNumberVo> findByName(String name) {
        Optional<BusNumberVo> bus = busRepository.findByname(name);
        return bus;
    }

    public void deleteById(Long busNo) {
        busRepository.deleteById(busNo);
    }

    public void updateById(Long busNo, BusNumberVo busInfo) {
        Optional<BusNumberVo> e = busRepository.findById(busNo);
        if (e.isPresent()) {
            e.get().setRouteId(busInfo.getRouteId());
            e.get().setName(busInfo.getName());
            busRepository.save(busInfo);
        }
    }

    public void updateByrouteId(Long routeId, BusNumberVo busInfo) {
        Optional<BusNumberVo> e = busRepository.findByrouteId(routeId);
        if (e.isPresent()) {
            e.get().setRouteId(busInfo.getRouteId());
            e.get().setName(busInfo.getName());
            busRepository.save(e.get());
        }
    }
}
