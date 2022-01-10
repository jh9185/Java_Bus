package com.example.java_bus.controller;

import com.example.java_bus.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    BoardService boardService;
    // prefix: /WEB-INF/views
    // suffix: .jsp
    // 풀경로: /WEB-INF/view/main.jsp
    @GetMapping(value = "/")
    public String MainJsp(Model model) {
        model.addAttribute("list", boardService.findAll());
        return "index";
    }

    @GetMapping("/boardUpload")
    public String boardUpload() { return "board/boardupload"; }
    @GetMapping("/Login")
    public String LoginJsp() {
        return "member/memberLogin";
    }
    @GetMapping("/Register")
    public String RegisterJsp() {
        return "member/memberRegister";
    }
}