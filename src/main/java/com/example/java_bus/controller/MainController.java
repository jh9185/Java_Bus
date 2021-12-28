package com.example.java_bus.controller;

import com.example.java_bus.service.BoardService;
import com.example.java_bus.service.BusstopService;
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

    private final BoardService service;
    private final BusstopService Busservice;

    @GetMapping("")
    public String Main(Model model) throws IOException {
        model.addAttribute("list", service.boardList());
        model.addAttribute("busstop", Busservice.busdataList());
        return "bootstrap/index";
    }

    @PostMapping("")
    public String mainBoard() {

        return "redirect:/index";
    }
}
