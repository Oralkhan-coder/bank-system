package kz.aitu.banksystem.keycloak.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import kz.aitu.banksystem.core.exeption.ServiceValidationException;
import kz.aitu.banksystem.core.model.statics.ErrorCode;
import kz.aitu.banksystem.keycloak.service.KeycloakService;
import kz.aitu.banksystem.registration.model.dto.request.RegistrationRequest;
import kz.aitu.banksystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.admin.client.resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private final UsersResource usersResource;
//    private final RolesResource rolesResource;
    private final RealmResource realmResource;
    private final UserService userService;
    @Autowired
    HttpServletRequest request;

    @Override
    public UserRepresentation register(RegistrationRequest request) {
        List<UserRepresentation> result = usersResource.search(request.getPhoneNumber(), true);
        log.info("Keycloak result: {}", result.size());

        UserRepresentation userRepresentation = result
                .stream()
                .findFirst()
                .orElseGet(() -> createNewUser(request));

        return updateUserDetails(request, userRepresentation);
    }

    @Override
    public UserRepresentation updateUserDetails(RegistrationRequest request, UserRepresentation user) {
        UserResource userResource = resetPassword(request.getPassword(), user.getId());
        RoleRepresentation roles = realmResource.roles().get(request.getRole().name()).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(roles));
        Long userId = userService.save(request, user.getId(), request.getRole().name());
        user.singleAttribute("profileId", userId.toString());
        userResource.update(user);
        return user;
    }

    private UserRepresentation createNewUser(RegistrationRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(request.getPhoneNumber());
        Response response = usersResource.create(user);

        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            String keycloakUserId = CreatedResponseUtil.getCreatedId(response);
            log.info("Created keycloak userId: {}", keycloakUserId);
            return user;
        } else {
            throw new ServiceValidationException(response.getEntity().toString(), ErrorCode.SYSTEM_ERROR);
        }
    }

    private UserResource resetPassword(String password, String userId) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);

        UserResource userResource = usersResource.get(userId);
        userResource.resetPassword(credential);
        return userResource;
    }
}
