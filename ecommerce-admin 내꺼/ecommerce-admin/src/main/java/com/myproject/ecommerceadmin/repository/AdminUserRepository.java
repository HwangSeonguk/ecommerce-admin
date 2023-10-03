package com.myproject.ecommerceadmin.repository;

import com.myproject.ecommerceadmin.domain.user.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByEmailAndIsActivated(String email, boolean isActivated);
}
