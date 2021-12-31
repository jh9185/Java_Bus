package com.example.java_bus.controller;

import com.example.java_bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    public String viewBusStation(Model model, Long busRouteId) throws IOException {
        model.addAttribute("busstationlist", busservice.getBusStationList(busRouteId));
        return "/busstation/busstationview.html";
    }

    @GetMapping("searchstationview")
    public String viewBusStation(Model model, String busNumber) throws IOException {
        // 넘버 -> ID 변환
        Long busRouteId = busservice.searchBusRouteId(busNumber);
        model.addAttribute("busstationlist", busservice.getBusStationList(busRouteId));
        return "/busstation/busstationview.html";
    }
}
