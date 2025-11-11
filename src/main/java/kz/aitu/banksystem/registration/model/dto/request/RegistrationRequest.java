package kz.aitu.banksystem.registration.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "first name must not be empty")
    @NotNull(message = "first name is required")
    private String firstName;

    @NotEmpty(message = "last name must not be empty")
    @NotNull(message = "last name is required")
    private String lastName;

    private String middleName;

    @NotEmpty(message = "phone number must not be empty")
    @NotNull(message = "phone number is required")
    private String phoneNumber;

    @NotEmpty(message = "password must not be empty")
    @NotNull(message = "password is required")
    private String password;

    @JsonIgnore
    private Long userId;
}
