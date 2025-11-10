package com.gopassa.auth_service.application.dto.entitiesDTO;

import com.gopassa.auth_service.domain.model.entities.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleDTO {
    private final String id;
    private final String designation;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static RoleDTO create(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getDesignation(),
                role.getCreatedAt(),
                role.getUpdatedAt()
        );
    }

    public static List<RoleDTO> createList(List<Role> roles) {

        return roles.stream()
                .map(RoleDTO::create)
                .toList();
    }
}
