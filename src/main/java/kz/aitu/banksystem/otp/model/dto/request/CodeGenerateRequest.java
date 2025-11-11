package kz.aitu.banksystem.otp.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.aitu.banksystem.otp.model.enums.CodeSendType;
import lombok.Data;

@Data
public class CodeGenerateRequest {

    @NotNull(message = "receiver is required")
    @NotEmpty(message = "receiver field is empty")
    private String receiver;

    @NotNull(message = "type is required")
    private CodeSendType type;

}