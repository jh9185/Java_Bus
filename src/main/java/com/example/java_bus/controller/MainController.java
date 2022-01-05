package com.example.java_bus.controller;

import com.example.java_bus.service.BoardService;
import com.example.java_bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = {"/", "/main"})
@RequiredArgsConstructor
public class MainController {

    private final BoardService boradservice;
    private final BusService busservice;

    @GetMapping("")
    public String Main(Model model) throws IOException {
        model.addAttribute("list", boradservice.boardList());
        model.addAttribute("busnumberlist", busservice.getBusNumberList());
        return "bootstrap/index";
    }

    @GetMapping("/busStationload")
    public String MainbusStationLoad(Model model) throws IOException {
        //busservice.BusStationLoadData();
        return "bootstrap/index";
    }
}
