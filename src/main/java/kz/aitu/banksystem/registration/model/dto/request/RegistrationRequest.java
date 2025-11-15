package kz.aitu.banksystem.registration.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.aitu.banksystem.registration.model.dto.BasicRegistrationData;
import kz.aitu.banksystem.registration.model.enums.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotNull(message = "role is required")
    private UserRoleType role;

    @NotEmpty(message = "code must not be empty")
    @NotNull(message = "code is required")
    private String code;

    private BasicRegistrationData data;
}
