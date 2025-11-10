package com.gopassa.auth_service.application.dto.requestDTO.creationDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateRoleDTO {
    @NotNull(message = "The designation must be filled.")
    @Size(min = 3, max = 160)
    private final String designation;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
