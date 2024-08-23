package com.upwork.url.shortener.util;

import lombok.experimental.UtilityClass;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class UrlSanitizer {

    public static String encodeUrl(String url) {
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }

    public static String decodeUrl(String url) {
        return URLDecoder.decode(url, StandardCharsets.UTF_8);
    }
}

