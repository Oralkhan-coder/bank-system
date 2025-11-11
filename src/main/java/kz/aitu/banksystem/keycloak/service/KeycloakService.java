package kz.aitu.banksystem.keycloak.service;

import kz.aitu.banksystem.registration.model.dto.request.RegistrationRequest;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {
    UserRepresentation register(RegistrationRequest request);
    UserRepresentation updateUserDetails(RegistrationRequest request, UserRepresentation user);
}
