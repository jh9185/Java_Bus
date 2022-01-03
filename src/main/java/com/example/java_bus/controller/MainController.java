package com.example.java_bus.controller;

import com.example.java_bus.service.BoardService;
import com.example.java_bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(value = {"/", "/main"})
@RequiredArgsConstructor
public class MainController {

    private final BoardService boradservice;
    private final BusService busservice;

    @GetMapping("")
    public String Main(Model model) throws IOException {
        model.addAttribute("list", boradservice.boardList());
        return "bootstrap/index";
    }

    @GetMapping("/busStationload")
    public String MainbusStationLoad(Model model) throws IOException {
        //busservice.BusStationLoadData();
        return "bootstrap/index";
    }

}
