package com.example.java_bus.mapper;

import com.example.java_bus.domain.BusStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusMapper {
    List<BusStation> getList();
}
