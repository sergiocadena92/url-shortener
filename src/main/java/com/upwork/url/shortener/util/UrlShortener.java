package com.upwork.url.shortener.util;

import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class UrlShortener {

    @Setter
    private static String alphabet;
    @Setter
    private static int length;

    private static final Random RANDOM = new Random();

    public static String shortenUrl(String url){
        StringBuilder shortCode = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(alphabet.length());
            shortCode.append(alphabet.charAt(index));
        }
        return shortCode.toString();
    }
}
