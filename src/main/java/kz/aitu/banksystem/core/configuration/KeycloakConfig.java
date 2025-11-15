package kz.aitu.banksystem.core.configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    private String authUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.client-key-password}")
    private String clientSecret;

    @Bean
    public Keycloak keycloakAdminClient() {
        return KeycloakBuilder.builder()
                .serverUrl(authUrl)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    @Bean
    public RealmResource realmResource(Keycloak keycloakAdminClient) {
        return keycloakAdminClient.realm(realm);
    }

    @Bean
    public UsersResource usersResource(RealmResource realmResource) {
        return realmResource.users();
    }

    @Bean
    public RolesResource rolesResource(RealmResource realmResource) {
        return realmResource.roles();
    }

}

