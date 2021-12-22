package com.example.java_bus.controller;

import com.example.java_bus.service.BoardService;
import com.example.java_bus.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("")
    public String Login() {
        return "bootstrap/login";
    }
}

