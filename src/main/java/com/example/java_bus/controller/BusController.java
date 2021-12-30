package com.example.java_bus.controller;

import com.example.java_bus.service.BusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BusController {

    private final BusService busservice;

    public BusController(BusService busservice) {
        this.busservice = busservice;
    }

    @GetMapping("/bus")
    public String callapi() throws IOException {
        return busservice.BusStationLoadData();
    }
}
