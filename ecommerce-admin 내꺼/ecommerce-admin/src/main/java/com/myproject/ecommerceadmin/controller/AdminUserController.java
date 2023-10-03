package com.myproject.ecommerceadmin.controller;

import com.myproject.ecommerceadmin.domain.user.AdminUser;
import com.myproject.ecommerceadmin.dto.AdminUserDTO;
import com.myproject.ecommerceadmin.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller

public class AdminUserController {
    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping(value = "/users/sign-up")
    public String adminUserForm(Model model){
        log.info(">>> 회원 가입 폼");
        return "users/sign-up";
    }
    @PostMapping(value = "/users/register")
    public String createAdminUser(AdminUserDTO adminUserDTO){
        log.info(">>> 회원 가입 진행, {}", adminUserDTO);
        AdminUser adminUser = adminUserDTO.toEntity();
        adminUserService.save(adminUser);
        return "/";
    }
    @GetMapping(value = "/users/login")
    public String loginForm(@RequestParam(value = "error", defaultValue = "false") boolean error, Model model) {
        if (error) {
            model.addAttribute("loginFailure", true);
            model.addAttribute("loginFailureMessage", "아이디와 패스워드를 확인하시고 다시 로그인해주세요.");
        }
        return "/users/login";
    }
}
