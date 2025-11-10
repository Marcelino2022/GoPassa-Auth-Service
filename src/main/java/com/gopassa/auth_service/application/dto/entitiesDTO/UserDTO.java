package com.gopassa.auth_service.application.dto.entitiesDTO;

import com.gopassa.auth_service.domain.model.entities.User;
import com.gopassa.auth_service.domain.model.enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

@Data
public class UserDTO {
    private final String id;
    private final String tenantId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String mobileNumber;
    private final String userType;
    private final UserStatus userStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Set<String> userRoles;


    public  static UserDTO create(User user) {
        return new UserDTO(
                user.getId(),
                user.getTenantId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getMobileNumber(),
                user.getUserType(),
                user.getUserStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                Optional
                    .ofNullable(user.getUserRoles())
                    .orElse(List.of())
                    .stream()
                    .map(ur -> ur.getRole().getDesignation())
                    .collect(toSet())

        );
    }
}
