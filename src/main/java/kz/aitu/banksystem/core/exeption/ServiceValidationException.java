package kz.aitu.banksystem.core.exeption;

import kz.aitu.banksystem.core.model.statics.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceValidationException extends RuntimeException {

    private String message;
    private ErrorCode errorCode;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ServiceValidationException(String message, ErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}