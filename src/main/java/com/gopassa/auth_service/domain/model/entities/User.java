package com.gopassa.auth_service.domain.model.entities;

import com.gopassa.auth_service.domain.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "users")
@SuppressWarnings("unused")
public class User {
    @Id
    @GeneratedValue(strategy = UUID)
    @Column(unique = true, nullable = false, length = 36)
    private String id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name="last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 60)
    private String email;

    @Column(name = "mobile_number", unique = true, nullable = false, length = 20)
    private String mobileNumber;

    @Column(name = "user_type", nullable = false, length = 20)
    private String userType;

    @Column(name = "user_status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "secret", nullable = true, length = 36)
    private String secret;

    @Column(name = "password", nullable = true, length = 255)
    private String password;

    @Column(name = "tenant_id", length = 36, nullable = false)
    private String tenantId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
