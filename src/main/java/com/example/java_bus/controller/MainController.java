package com.example.java_bus.controller;

import com.example.java_bus.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/main"})
@RequiredArgsConstructor
public class MainController {

    private final BoardService service;

    @GetMapping("")
    public String Main(Model model){
        model.addAttribute("list", service.boardList());
        return "/bootstrap/index";
    }

    @PostMapping("")
    public String mainBoard(){
        return "redirect:/index";
    }
}
