package com.example.commerce.member.controller;

import com.example.commerce.member.model.MemberParam;
import com.example.commerce.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController extends BaseController {

    private final MemberService memberService;


    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model,
                                 MemberParam memberParam) {

        boolean register = memberService.register(memberParam);
        model.addAttribute("result", register);

        return "member/register_complete";
    }

    @GetMapping("/member/email_auth")
    public String emailAuth(HttpServletRequest request,
                            Model model) {
        String AuthKey = request.getParameter("id");

        boolean emailAuth = memberService.emailAuth(AuthKey);
        model.addAttribute("result", emailAuth);

        return "member/email_auth";
    }
}