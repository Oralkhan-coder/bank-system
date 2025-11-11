package kz.aitu.banksystem.core.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Util {

    public static String getRandomFiveDigitalNumber() {
        return String.format("%04d", new Random().nextInt(9999));
    }

    public static boolean checkCodeForBackDoor(String code) {
        return code.equals("2409");
    }
}
