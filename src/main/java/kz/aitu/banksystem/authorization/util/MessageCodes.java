package kz.aitu.banksystem.authorization.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageCodes {

    public static final String WRONG_PASSWORD = "wrong_password";
    public static final String PHONE_ALREADY_EXISTS = "phone_already_exists";
}
