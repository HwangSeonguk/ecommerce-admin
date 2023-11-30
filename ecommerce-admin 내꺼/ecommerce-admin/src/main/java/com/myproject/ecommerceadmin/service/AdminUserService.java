package com.myproject.ecommerceadmin.service;

import com.myproject.ecommerceadmin.adapter.AdminUserAdapter;
import com.myproject.ecommerceadmin.domain.user.AdminUser;
import com.myproject.ecommerceadmin.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdminUserService implements UserDetailsService {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUser insert(AdminUser adminUser) {

        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        AdminUser insertedAdminUser = adminUserRepository.save(adminUser);
        log.info("insertedAdminUser : {}", insertedAdminUser);
        return insertedAdminUser;
    }

    public AdminUser delete(AdminUser adminUser) {
        adminUser.setActivated(false);
        adminUser.setDeleted(true);
        adminUser.setUpdatedAt(OffsetDateTime.now());
        AdminUser deletedAdminUser = adminUserRepository.save(adminUser);
        log.info("deletedAdminUser : {}", deletedAdminUser);
        return deletedAdminUser;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
        Optional<AdminUser> optionalAdminUser = adminUserRepository.findByEmailAndIsActivated(userEmail, true);
        if(optionalAdminUser.isEmpty()){
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다");
        }
        AdminUser adminUser = optionalAdminUser.get();
        return new AdminUserAdapter(adminUser);
    }

}
