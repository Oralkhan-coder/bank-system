package kz.aitu.banksystem.authorization.service;

import org.keycloak.representations.AccessTokenResponse;

public interface AuthService {
    AccessTokenResponse authenticate(String phoneNumber, String password);
    void checkCode(String phoneNumber, String code);
    void checkExistence(String phone);
}
