package com.myproject.ecommerceadmin.adapter;

import com.myproject.ecommerceadmin.domain.user.AdminUser;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
@Getter
public class AdminUserAdapter extends User {
    private final AdminUser adminUser;
    public AdminUserAdapter(AdminUser adminUser) {
        super(adminUser.getEmail(), adminUser.getPassword(), List.of(new SimpleGrantedAuthority(adminUser.getRole().toString())));
        this.adminUser = adminUser;
    }
}
