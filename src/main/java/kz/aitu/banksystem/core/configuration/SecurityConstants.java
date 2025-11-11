package kz.aitu.banksystem.core.configuration;

public class SecurityConstants {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_WITH_PHONE_NUMBER_URL = "/users/register";
    public static final String SIGN_UP_WITH_EMAIL_URL = "/users/register-with-email";
    public static final String COUNTRIES_URL = "/countries/**";
    public static final String CITIES_URL = "/cities/**";
    public static final String CODES_URL = "/codes/**";
    public static final String PROFILE_VALIDATE_URL = "/profiles/current/profile";
    public static final String PHONE_NUMBER_EXIST_URL = "/users/phone-number/check";
    public static final String EMAIL_EXIST_URL = "/users/email/check";
    public static final String USERNAME_EXIST_URL = "/profiles/username/check";
    public static final String SIGN_UP_VALIDATE_URL = "/users/validate";
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_EXIST_URL = "/users/login/check";
    public static final String FORGOT_PASSWORD_URL = "/users/forgot/password";
    public static final String OTP_URL = "/otp/**";
}