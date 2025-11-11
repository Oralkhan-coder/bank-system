package kz.aitu.banksystem.otp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodeCheckRequest {
    @NotNull(message = "receiver is required")
    @NotEmpty(message = "receiver field is empty")
    private String receiver;

    @NotNull(message = "code field is required")
    @NotEmpty(message = "code field is empty")
    @Size(min = 4, max = 4, message = "code field size is not 4")
    private String code;
}
