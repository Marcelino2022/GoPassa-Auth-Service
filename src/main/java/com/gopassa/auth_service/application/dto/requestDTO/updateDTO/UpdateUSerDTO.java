package com.gopassa.auth_service.application.dto.requestDTO.updateDTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UpdateUSerDTO {
    @NotNull(message = "The first name must be filled.")
    @Size(min = 3, max = 50)
    private final String firstName;

    @NotNull(message = "The last name must be filled.")
    @Size(min = 3, max = 50)
    private final String lastName;

    @NotNull(message = "The email must be filled.")
    private final String email;

    @NotNull(message = "The mobile number must be filled.")
    @Size(min = 9, max = 20)
    private final String mobileNumber;

    @NotNull(message = "The user type must be indicated.")
    @Size(min = 4, max = 20)
    private final String userType;

    @NotNull(message = "The user status must be filled")
    @Enumerated(EnumType.STRING)
    private final String userStatus;

    private LocalDateTime updatedAt;
}
