package com.example.java_bus.mapper;

import com.example.java_bus.domain.BusNumber;
import com.example.java_bus.domain.BusStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusMapper {
    //List<BusStation> getList();

    List<BusNumber> getNumberList();

    List<BusStation> getStationList();

    void insertBusNumberList(BusNumber busNumber);

    BusNumber getBusRouteId(String busRouteNm);
}
