package com.myproject.ecommerceadmin.dto.user;

import com.myproject.ecommerceadmin.enums.AdminUserPermission;
import com.myproject.ecommerceadmin.enums.AdminUserRole;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AdminUserFormDTO {
    private String username;
    private String email;
    private String password1;
    private String password2;

    public AdminUserDTO toDTO(){
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        adminUserDTO.setUsername(username);
        adminUserDTO.setPassword(password1);
        adminUserDTO.setEmail(email);
        adminUserDTO.setRole(AdminUserRole.USER);
        adminUserDTO.setPermission(AdminUserPermission.ALL);

        OffsetDateTime now = OffsetDateTime.now();
        adminUserDTO.setCreatedAt(now);
        adminUserDTO.setUpdatedAt(now);
        adminUserDTO.setActivated(true);
        return adminUserDTO;
    }
}

