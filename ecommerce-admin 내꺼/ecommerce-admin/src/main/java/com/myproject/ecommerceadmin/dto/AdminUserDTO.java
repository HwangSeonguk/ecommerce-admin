package com.myproject.ecommerceadmin.dto;

import com.myproject.ecommerceadmin.domain.user.AdminUser;
import com.myproject.ecommerceadmin.enums.AdminUserPermission;
import com.myproject.ecommerceadmin.enums.AdminUserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AdminUserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private AdminUserRole role;
    private AdminUserPermission permission;
    private boolean isDeleted = false;
    private boolean isActivated = true;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public AdminUser toEntity() {
        return AdminUser.builder().id(id).username(username).email(email).password(password).role(role)
                .permission(permission).isDeleted(isDeleted).isActivated(isActivated)
                .createdAt(createdAt).updatedAt(updatedAt).build();
    }
}
