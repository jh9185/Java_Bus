package com.example.java_bus.controller;

import com.example.java_bus.domain.BusStation;
import com.example.java_bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bus/*")
@RequiredArgsConstructor
public class BusController {
    private final BusService busservice;

    @GetMapping("")
    public String searchStationapi(Model model) throws IOException {
        //model.addAttribute("busstationlist", busservice.BusStationLoadData());
        return "/busstation/busstationview.html";
    }

    @PostMapping("numberupload")
    public String busNumberUpload(Model model) throws IOException {
        busservice.readBusNumber();
        return "/busstation/busnumberview.html";
    }

    @GetMapping("numberview")
    public String viewBusNumber(Model model){
        model.addAttribute("busnumberlist", busservice.getBusNumberList());
        return "/busstation/busnumberview.html";
    }

    @GetMapping("stationview")
    public String viewBusStation(Model model, String busNumber) throws IOException {
        // 넘버 -> ID 변환
        Long busRouteId = busservice.searchBusRouteId(busNumber);
        List<BusStation> busStations = busservice.getBusStationList(busRouteId);
        Point2D CenterPos = busservice.getBusStationsCenter(busStations);
        model.addAttribute("busstationlist", busStations);
        model.addAttribute("busstationscenter", CenterPos);

        return "/busstation/busstationview.html";
    }

}
