package kz.aitu.banksystem.core.configuration;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.resource}")
    private String CLIENT_ID;

    @Value("${keycloak.auth-server-url}")
    private String AUTH_URL;

    @Value("${keycloak.realm}")
    private String REALM;

    @Value("${keycloak.client-key-password}")
    private String SECRET;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder().serverUrl(AUTH_URL)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS).realm(REALM).clientId(CLIENT_ID)
                .clientSecret(SECRET)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(15).build()).build();
    }

    @Bean
    public AuthzClient authzClient() {
        Map<String, Object> clientSecret = new HashMap<>();
        clientSecret.put("secret", SECRET);
        org.keycloak.authorization.client.Configuration configuration =
                new org.keycloak.authorization.client.Configuration(AUTH_URL, REALM, CLIENT_ID, clientSecret, null);
        return AuthzClient.create(configuration);
    }

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }


    @Bean
    public RealmResource realmResource() {
        return keycloak().realm(REALM);
    }

    @Bean
    public UsersResource usersResource() {
        return realmResource().users();
    }
    @Bean
    public RolesResource roleResource() {
        return realmResource().roles();
    }

}
