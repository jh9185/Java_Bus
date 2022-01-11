package com.example.java_bus.repository;

import com.example.java_bus.vo.BusNumberVo;
import com.example.java_bus.vo.BusStationVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<BusNumberVo, Long> {
    public List<BusNumberVo> findById(String busNo);

    Optional<BusNumberVo> findByrouteId(Long routeId);
    Optional<BusNumberVo> findByname(String name);
}
