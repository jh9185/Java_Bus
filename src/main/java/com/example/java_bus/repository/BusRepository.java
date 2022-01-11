package com.example.java_bus.repository;

import com.example.java_bus.vo.BusStationVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<BusStationVo, Long> {
    public List<BusStationVo> findById(String busNo);
}
