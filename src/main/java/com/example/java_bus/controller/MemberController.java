package com.example.java_bus.controller;

import com.example.java_bus.domain.Member;
import com.example.java_bus.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("addmember")
    public String AddMember(Member member) throws Exception {
        memberService.AddMember(member);
        return "bootstrap/login";
    }

    @PostMapping("loginCheck")
    public String LoginMember(Member member) throws Exception {
        memberService.LoginCheck(member);
        return "bootstrap/index.html";
    }
}

