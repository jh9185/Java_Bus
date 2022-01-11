package com.example.java_bus.controller;

import com.example.java_bus.bus.BusStation;
import com.example.java_bus.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class BusController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BusService busService;

    @GetMapping("/busNumberFileUpload")
    public String boardUpload() throws IOException {
        BusStation busStation = new BusStation();
        busStation.readBusNumber();
        return "index";
    }

}
