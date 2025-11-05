package kz.aitu.banksystem.user.converter;

import kz.aitu.banksystem.user.model.dto.UserViewResponse;
import kz.aitu.banksystem.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<User, UserViewResponse> {

    @Override
    public UserViewResponse convert(User source) {
        return UserViewResponse.builder()
                .firstName(source.getFirstName())
                .middleName(source.getMiddleName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .phoneNumber(String.valueOf(source.getPhoneNumber()))
                .build();
    }
}