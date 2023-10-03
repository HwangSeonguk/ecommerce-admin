package com.myproject.ecommerceadmin.service;

import com.myproject.ecommerceadmin.domain.user.AdminUser;
import com.myproject.ecommerceadmin.repository.AdminUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    public Optional<AdminUser> save(AdminUser adminUser) {

        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        return Optional.of(adminUserRepository.save(adminUser));
    }

    public AdminUser findByEmailAndActivated(String email, boolean isActivated){
        Optional<AdminUser> adminUser = adminUserRepository.findByEmailAndIsActivated(email, isActivated);
        log.info("adminUser : {}", adminUser);
        return adminUser.orElse(null);
    }
}
