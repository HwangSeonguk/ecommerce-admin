package com.myproject.ecommerceadmin.controller;

import com.myproject.ecommerceadmin.dto.AdminUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller
public class HomeController {
    @GetMapping(value = {"/", "/index", "/home"})
    public String index(@AuthenticationPrincipal AdminUserDTO adminUserDTO, Model model) {

        log.info(">>> Login Admin User, {}", adminUserDTO);

        model.addAttribute("dailyPaymentCnt", 1_000_000);
        model.addAttribute("dailyCancelCnt", 50);
        model.addAttribute("dailyCustomerJoinCnt", 200);
        model.addAttribute("dailyCustomerWithdrawalCnt", 30);
        model.addAttribute("dailyProductRegCnt", 5_000);

        model.addAttribute("username", adminUserDTO.getUsername());

        return "index";
    }
}
