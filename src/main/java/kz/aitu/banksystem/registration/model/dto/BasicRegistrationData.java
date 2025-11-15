package kz.aitu.banksystem.registration.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BasicRegistrationData {
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
