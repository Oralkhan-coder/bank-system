package kz.aitu.banksystem.authorization.controller;

import kz.aitu.banksystem.authorization.model.LoginRequestDto;
import kz.aitu.banksystem.authorization.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public AccessTokenResponse login(@RequestBody @Validated LoginRequestDto request) {
        return authService.authenticate(request.getPhoneNumber(), request.getPassword());
    }
}
