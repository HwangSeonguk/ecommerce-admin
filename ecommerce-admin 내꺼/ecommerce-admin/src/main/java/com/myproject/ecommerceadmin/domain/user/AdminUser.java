package com.myproject.ecommerceadmin.domain.user;

import com.myproject.ecommerceadmin.dto.AdminUserDTO;
import com.myproject.ecommerceadmin.enums.AdminUserPermission;
import com.myproject.ecommerceadmin.enums.AdminUserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users", schema = "ecommerce")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private AdminUserRole role;
    @Column(name = "permission")
    @Enumerated(value = EnumType.STRING)
    private AdminUserPermission permission;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "is_activated")
    private boolean isActivated;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
