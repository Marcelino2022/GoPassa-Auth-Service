package com.gopassa.auth_service.application.dto.requestDTO.creationDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@SuppressWarnings ("unused")
public class CreateUserRoleTDO {
    @NotNull(message = "The User must be indicated.")
    @Size(min = 36, max = 36)
    private final String userId;

    @NotNull(message = "The Role must be indicated.")
    @Size(min = 36, max = 36)
    private final String roleId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
