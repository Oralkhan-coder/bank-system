package kz.aitu.banksystem.authorization.service.impl;

import kz.aitu.banksystem.authorization.service.AuthService;
import kz.aitu.banksystem.core.exeption.ServiceValidationException;
import kz.aitu.banksystem.core.model.statics.ErrorCode;
import kz.aitu.banksystem.keycloak.service.KeycloakService;
import kz.aitu.banksystem.keycloak.token.TokenClient;
import kz.aitu.banksystem.otp.model.dto.response.CodeResponse;
import kz.aitu.banksystem.otp.service.CodeService;
import kz.aitu.banksystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

import static kz.aitu.banksystem.authorization.util.MessageCodes.PHONE_ALREADY_EXISTS;
import static kz.aitu.banksystem.authorization.util.MessageCodes.WRONG_PASSWORD;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CodeService codeService;
    private final UserService userService;
    private final TokenClient tokenClient;
    private final KeycloakService keycloakService;

    @Override
    public AccessTokenResponse authenticate(String phoneNumber, String password) {
        return tokenClient.getToken(phoneNumber, password);
    }

    @Override
    public void checkCode(String phoneNumber, String code) {
        CodeResponse response = codeService.checkReceiverCode(phoneNumber, code);

        if (!response.isFound()) {
            throw new ServiceValidationException(WRONG_PASSWORD, ErrorCode.INVALID_ARGUMENT);
        }
    }

    @Override
    public void checkExistence(String phone) {
        if (userService.findByPhoneNumber(phone).isPresent()) {
            throw new ServiceValidationException(PHONE_ALREADY_EXISTS, ErrorCode.INVALID_ARGUMENT);
        }
    }
}
