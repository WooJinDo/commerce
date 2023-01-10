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
        System.out.println("");
        boolean emailAuth = memberService.emailAuth(AuthKey);
        model.addAttribute("result", emailAuth);

        return "member/email_auth";
    }

    @RequestMapping("/member/login")
    public String login() {

        return "member/login";
    }

    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(MemberParam memberParam,
                                     Model model) {
        boolean result = false;
        try {
            result = memberService.sendResetPassword(memberParam);
        } catch (Exception e) {
        }
        model.addAttribute("result", result);

        return "member/find_password_result";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(HttpServletRequest request, Model model) {
        String resetPasswordKey = request.getParameter("id");

        boolean result = false;
        try {
            result = memberService.checkResetPassword(resetPasswordKey);
        } catch (Exception e) {
        }
        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(HttpServletRequest request,
                                      Model model,
                                      MemberParam memberParam) {
        String resetPasswordKey = request.getParameter("id");

        boolean result = false;
        try {
            result = memberService.resetPassword(resetPasswordKey, memberParam.getUserPassword());
        } catch (Exception e) {
        }
        model.addAttribute("result", result);

        return "member/reset_password_result";
    }

}
