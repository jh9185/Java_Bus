package com.example.java_bus.repository;

import com.example.java_bus.vo.BusNumberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<BusNumberVo, Long> {
    public List<BusNumberVo> findById(String busNo);

    public Optional<BusNumberVo> findBybusrouteId(Long busrouteId);
    public Optional<BusNumberVo> findByname(String name);

    @Modifying
    @Query(value = "alter table bus alter column id RESTART WITH 1", nativeQuery = true)
    public void resetId();
}
