package com.gopassa.auth_service.domain.model.entities;

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
@Table(name = "roles")

public class Role {
    @Id
    @GeneratedValue(strategy = UUID)
    @Column(unique = true, length = 36)
    private String Id;

    @Column(name="designation", unique = true, nullable = false, length = 160)
    private String designation;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
