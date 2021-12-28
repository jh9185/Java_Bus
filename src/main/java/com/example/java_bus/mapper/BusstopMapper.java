package com.example.java_bus.mapper;

import com.example.java_bus.domain.Busstop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusstopMapper {
    List<Busstop> getList();
}
