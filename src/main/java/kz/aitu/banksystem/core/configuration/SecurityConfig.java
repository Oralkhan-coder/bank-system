package kz.aitu.banksystem.core.configuration;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@RequiredArgsConstructor
public class SecurityConfig {

    // Keycloak Authentication Provider
    @Bean
    public KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
        KeycloakAuthenticationProvider provider = new KeycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        return provider;
    }

    // AuthenticationManager Bean (for @Autowired if needed)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Session strategy for Keycloak (needed if you use session-based auth)
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new org.springframework.security.core.session.SessionRegistryImpl());
    }

    // SecurityFilterChain replaces WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_WITH_PHONE_NUMBER_URL).permitAll()
                        .requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_WITH_EMAIL_URL).permitAll()
                        .requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_VALIDATE_URL).permitAll()
                        .requestMatchers(SecurityConstants.PHONE_NUMBER_EXIST_URL).permitAll()
                        .requestMatchers(SecurityConstants.PROFILE_VALIDATE_URL).permitAll()
                        .requestMatchers(SecurityConstants.FORGOT_PASSWORD_URL).permitAll()
                        .requestMatchers(SecurityConstants.USERNAME_EXIST_URL).permitAll()
                        .requestMatchers(SecurityConstants.LOGIN_EXIST_URL).permitAll()
                        .requestMatchers(SecurityConstants.EMAIL_EXIST_URL).permitAll()
                        .requestMatchers(SecurityConstants.CODES_URL).permitAll()
                        .requestMatchers(SecurityConstants.LOGIN_URL).permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/internal/**").permitAll()
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**"
                        ).permitAll()
                        .requestMatchers(SecurityConstants.OTP_URL).permitAll()
                        // Role-based endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/roles/**").hasAnyRole("ADMIN", "MANAGER", "AUTHOR")

                        // All other requests require authentication
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    // CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "https://admin.ustaz.media",
                "https://ustaz.media",
                "https://www.ustaz.media",
                "https://api.ustaz.media"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
