package com.example.commerce.member.controller;

import com.example.commerce.member.model.MemberParam;
import com.example.commerce.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/register")
    public String registerSubmit(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model,
                                 MemberParam memberParam) {

        boolean saveMember = memberService.register(memberParam);
        model.addAttribute("result", saveMember);

        return "member/register_complete";
    }

    @GetMapping("/email_auth")
    public String emailAuth(HttpServletRequest request,
                            Model model) {
        String AuthKey = request.getParameter("id");
        boolean emailAuth = memberService.emailAuth(AuthKey);
        model.addAttribute("result", emailAuth);

        return "member/email_auth";
    }

    @RequestMapping("/login")
    public String login() {

        return "member/login";
    }


}
