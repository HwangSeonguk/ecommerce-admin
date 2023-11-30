package com.myproject.ecommerceadmin.controller;

import com.myproject.ecommerceadmin.annotation.AuthUser;
import com.myproject.ecommerceadmin.domain.user.AdminUser;
import com.myproject.ecommerceadmin.dto.user.AdminUserFormDTO;
import com.myproject.ecommerceadmin.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    @GetMapping(value = "/user/signup")
    public String adminUserForm(Model model){
        log.info(">>> 회원 가입 폼");
        return "/user/signup";
    }
    @PostMapping(value = "/user/sign-up")
    public String createAdminUser(AdminUserFormDTO adminUserFormDTO){
        log.info(">>> 회원 가입 진행, {}", adminUserFormDTO);
        log.info(">>> 회원 가입 결과, {}", adminUserService.insert(adminUserFormDTO.toDTO().toEntity()));

        return "redirect:/user/login";
    }

    @GetMapping(value = "/user/delete")
    public String deleteAdminUser(@AuthUser AdminUser adminUser) {
        log.info(">>> 회원 탈퇴 진행, {}", adminUser);
        adminUserService.delete(adminUser);
        SecurityContextHolder.clearContext();

        return "/user/login";
    }


}
