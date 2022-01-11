package com.example.java_bus.service.impl;

import com.example.java_bus.repository.BusRepository;
import com.example.java_bus.service.BusService;
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

    public List<BusStationVo> findAll() {
        List<BusStationVo> busLists = new ArrayList<>();
        busRepository.findAll().forEach(e -> busLists.add(e));
        return busLists;
    }
    public Optional<BusStationVo> findById(Long busNo) {
        Optional<BusStationVo> bus = busRepository.findById(busNo);
        return bus;
    }
    public void deleteById(Long mbrNo) {
        busRepository.deleteById(mbrNo);
    }
    public BusStationVo save(BusStationVo board) {
        busRepository.save(board);
        return board;
    }
    public void updateById(Long busNo, BusStationVo busInfo) {
        Optional<BusStationVo> e = busRepository.findById(busNo);
        if (e.isPresent()) {
            busRepository.save(busInfo);
        }
    }
}
