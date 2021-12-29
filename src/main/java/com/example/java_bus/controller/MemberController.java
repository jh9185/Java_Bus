package com.example.java_bus.controller;

import com.example.java_bus.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public String Login() {
        return "bootstrap/login";
    }

    @GetMapping("/register")
    public String MemberRegister() {
        return "bootstrap/register.html";
    }


}

