package com.example.java_bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    @GetMapping("/hello")
    public String Hello(){
        return "/boards/hello";
    }
}
