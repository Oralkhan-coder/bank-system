package kz.aitu.banksystem.otp.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.aitu.banksystem.core.model.dto.BaseDto;
import lombok.Data;

@Data
public class CodeDto extends BaseDto {
    @NotEmpty(message = "Otp must not be empty")
    @NotNull(message = "Otp must not be null")
    private String code;

    @NotEmpty(message = "Phone number must not be empty")
    @NotNull(message = "Phone number must not be null")
    private String phoneNumber;
}
