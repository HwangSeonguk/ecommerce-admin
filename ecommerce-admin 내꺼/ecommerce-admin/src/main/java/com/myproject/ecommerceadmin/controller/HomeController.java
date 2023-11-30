package com.myproject.ecommerceadmin.controller;

import com.myproject.ecommerceadmin.annotation.AuthUser;
import com.myproject.ecommerceadmin.domain.user.AdminUser;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller
public class HomeController {
    @GetMapping(value = {"/", "/index", "/home"})
    public String index(@AuthUser AdminUser adminUser, Model model) {

        log.info(">>> Login Admin User, {}", adminUser);

        model.addAttribute("dailyPaymentCnt", 1_000_000);
        model.addAttribute("dailyCancelCnt", 50);
        model.addAttribute("dailyCustomerJoinCnt", 200);
        model.addAttribute("dailyCustomerWithdrawalCnt", 30);
        model.addAttribute("dailyProductRegCnt", 5_000);
        if(adminUser != null){
            model.addAttribute("username", adminUser.getUsername()+"님 환영합니다");
        }


        return "index";
    }
}
