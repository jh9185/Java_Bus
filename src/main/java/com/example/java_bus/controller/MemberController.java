package com.example.java_bus.controller;

import com.example.java_bus.domain.Member;
import com.example.java_bus.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    public String AddMember(Model model, Member member) throws Exception {
        boolean result = memberService.AddMember(member);
        return "bootstrap/login";
    }

    @ResponseBody
    @RequestMapping("idcheck")
    public String ID_Check(@RequestBody String paramData) throws Exception {
        //클라이언트가 보낸 ID값
        String ID = paramData.trim();
        System.out.println(ID);
        Integer result = memberService.IdCheck(paramData);

        if(result != null) {//결과 값이 있으면 아이디 존재
            return "-1";
        } else {		//없으면 아이디 존재 X
            System.out.println("null");
            return "0";
        }
    }

    @PostMapping("loginCheck")
    public String LoginMember(Member member) throws Exception {
        memberService.LoginCheck(member);
        return "bootstrap/index.html";
    }
}

