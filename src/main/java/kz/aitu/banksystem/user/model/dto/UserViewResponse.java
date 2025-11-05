package kz.aitu.banksystem.user.model.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserViewResponse {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phoneNumber;
}
