package com.misc.note.common.util;

import java.security.SecureRandom;
import java.util.Random;

public class CommonUtil {

    private static final String MIXED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final String NUMBERS = "0123456789";

    private static final Random RANDOM = new SecureRandom();

    public static String randomString(int length, int type) {
        char[] nonceChars = new char[length];
        String randomString = "";
        if (type == 0) {
            randomString = NUMBERS;
        }else if (type == 1) {
            randomString = CHARS;
        }else{
            randomString = MIXED_CHARS;
        }
        for (int i = 0; i < nonceChars.length; i++) {
            nonceChars[i] = randomString.charAt(RANDOM.nextInt(randomString.length()));
        }
        return new String(nonceChars);
    }
}
