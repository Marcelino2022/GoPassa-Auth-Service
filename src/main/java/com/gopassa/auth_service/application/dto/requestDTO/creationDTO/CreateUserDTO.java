package com.gopassa.auth_service.application.dto.requestDTO.creationDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CreateUserDTO {

    @Size(min = 36, max = 36)
    private String tenantId;

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
    private final String userStatus;

    @NotNull
    @Size(min = 36, max = 36)
    private final List<String> roles;

    @NotNull
    @Size(min = 36, max = 36)
    private String userId;

    private final String password;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
