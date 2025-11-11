package kz.aitu.banksystem.otp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;

public enum CodeSendType {

    EMAIL(1),
    Telegram(0);

    private int value;

    CodeSendType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Nullable
    public static CodeSendType resolve(int statusCode) {
        for (CodeSendType type : values()) {
            if (type.value == statusCode) {
                return type;
            }
        }
        return null;
    }

    @JsonCreator
    public CodeSendType fromInteger(int num) {
        return resolve(num);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
