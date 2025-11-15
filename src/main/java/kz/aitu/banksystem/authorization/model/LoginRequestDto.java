package kz.aitu.banksystem.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestDto {
    @NotNull(message = "Phone number is required")
    @NotEmpty(message = "Phone number must not be empty")
    private String phoneNumber;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password must not be empty")
    private String password;
}
