package com.gopassa.auth_service.application.dto.requestDTO.updateDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@SuppressWarnings("unused")
public class UpdateUserRoleTDO {

    @NotNull(message = "The Role must be indicated.")
    @Size(min = 36, max = 36)
    private final String roleId;

    private final LocalDateTime updatedAt;
}
