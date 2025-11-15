package kz.aitu.banksystem.registration.controller;

import kz.aitu.banksystem.registration.model.dto.request.RegistrationRequest;
import kz.aitu.banksystem.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService service;

    @PostMapping
    public AccessTokenResponse register(@RequestBody RegistrationRequest request) {
        return service.register(request);
    }
}
