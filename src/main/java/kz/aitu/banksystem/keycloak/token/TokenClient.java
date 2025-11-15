package kz.aitu.banksystem.keycloak.token;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.keycloak.representations.AccessTokenResponse;

@Component
@RequiredArgsConstructor
public class TokenClient {

    @Value("${keycloak.auth-server-url}")
    private String authUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.client-key-password}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public AccessTokenResponse getToken(String username, String password) {

        String url = authUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body =
                "client_id=" + clientId +
                        "&client_secret=" + clientSecret +
                        "&grant_type=password" +
                        "&username=" + username +
                        "&password=" + password;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<AccessTokenResponse> response =
                restTemplate.exchange(url, HttpMethod.POST, request, AccessTokenResponse.class);

        return response.getBody();
    }
}
