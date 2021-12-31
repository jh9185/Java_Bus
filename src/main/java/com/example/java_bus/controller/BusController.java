package com.example.java_bus.controller;

import com.example.java_bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BusController {
    private final BusService busservice;

    @GetMapping("/bus")
    public String searchStationapi(Model model) throws IOException {
        //model.addAttribute("busstationlist", busservice.BusStationLoadData());
        return "/busstation/busstationview.html";
    }
//
    @PostMapping("/bus")
    public String busNumberUpload(Model model) throws IOException {
        model.addAttribute("busnumberlist", busservice.readBusNumber());
//        model.addAttribute("busstationlist", busservice.BusStationLoadData());
        return "/busstation/busnumberview.html";
    }
}
