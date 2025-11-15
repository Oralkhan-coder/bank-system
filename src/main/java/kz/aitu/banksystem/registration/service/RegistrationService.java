package kz.aitu.banksystem.registration.service;

import kz.aitu.banksystem.registration.model.dto.request.RegistrationRequest;
import org.keycloak.representations.AccessTokenResponse;

public interface RegistrationService {
    AccessTokenResponse register(RegistrationRequest request);
}
