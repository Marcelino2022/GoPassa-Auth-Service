package com.gopassa.auth_service.application.dto.entitiesDTO;
import com.gopassa.auth_service.domain.model.entities.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoleDTO {
    private final String id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final UserDTO user;
    private final RoleDTO role;

    public static UserRoleDTO create(UserRole userRole) {
        return new UserRoleDTO(
            userRole.getId(),
            userRole.getCreatedAt(),
            userRole.getUpdatedAt(),
            UserDTO.create(userRole.getUser()),
            RoleDTO.create(userRole.getRole())
        );
    }
}
