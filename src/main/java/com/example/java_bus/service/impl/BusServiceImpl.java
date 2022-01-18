package com.example.java_bus.service.impl;

import com.example.java_bus.repository.BusRepository;
import com.example.java_bus.service.BusService;
import com.example.java_bus.vo.BusNumberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    public List<BusNumberVo> saveAll(List<BusNumberVo> busNumbers) {
        busRepository.saveAll(busNumbers);
        return busNumbers;
    }

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

    public Optional<BusNumberVo> findByrouteId(Long busrouteId) {
        Optional<BusNumberVo> bus = busRepository.findBybusrouteId(busrouteId);
        return bus;
    }

    public Optional<BusNumberVo> findByName(String name) {
        Optional<BusNumberVo> bus = busRepository.findByname(name);
        return bus;
    }

    public void deleteAll() { busRepository.deleteAll(); }
    public void deleteById(Long busNo) {
        busRepository.deleteById(busNo);
    }

    public void updateById(Long busNo, BusNumberVo busInfo) {
        Optional<BusNumberVo> e = busRepository.findById(busNo);
        if (e.isPresent()) {
            e.get().setBusrouteId(busInfo.getBusrouteId());
            e.get().setName(busInfo.getName());
            busRepository.save(busInfo);
        }
    }

    public void updateByrouteId(Long busrouteId, BusNumberVo busInfo) {
        Optional<BusNumberVo> e = busRepository.findBybusrouteId(busrouteId);
        if (e.isPresent()) {
            e.get().setBusrouteId(busInfo.getBusrouteId());
            e.get().setName(busInfo.getName());
            busRepository.save(e.get());
        }
    }

    public void resetId(){
        busRepository.resetId();
    }
}
