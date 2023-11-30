package com.myproject.ecommerceadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
public class LoginController {
    @GetMapping(value = "/user/login")
    public String loginForm(@RequestParam(value = "error", required = false) boolean error, @RequestParam(value = "exception", required = false) String exception, Model model) {

        model.addAttribute("exception", exception);

        return "/user/login";
    }

    @GetMapping(value = "/user/logout")
    public String logout(SessionStatus sessionStatus) {
        if(sessionStatus.isComplete()){
            sessionStatus.setComplete();
        }

        return "/user/login";
    }
}
