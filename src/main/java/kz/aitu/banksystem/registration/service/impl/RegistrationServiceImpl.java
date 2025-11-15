package kz.aitu.banksystem.registration.service.impl;

import kz.aitu.banksystem.account.service.AccountService;
import kz.aitu.banksystem.authorization.service.AuthService;
import kz.aitu.banksystem.keycloak.service.KeycloakService;
import kz.aitu.banksystem.registration.model.dto.request.RegistrationRequest;
import kz.aitu.banksystem.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final AuthService authService;
    private final KeycloakService keycloakService;
    private final AccountService accountService;

    @Override
    public AccessTokenResponse register(RegistrationRequest request) {
        authService.checkCode(request.getData().getPhoneNumber(), request.getCode());
        authService.checkExistence(request.getData().getPhoneNumber());
        UserRepresentation userRepresentation = keycloakService.register(request);

        request.getData().setUserId(userRepresentation.getAttributes()
                .get("profileId")
                .stream()
                .findFirst()
                .map(Long::valueOf)
                .orElse(null));

        // TODO: We should add new Entity to Currency and enums as well! What Heck is this!!! Not Clean Code
        accountService.createAccount(request.getData().getPhoneNumber(), 500.0,
                "Dollar", request.getData().getUserId());

        return authService.authenticate(request.getData().getPhoneNumber(), request.getData().getPassword());
    }
}
