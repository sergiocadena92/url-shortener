package com.upwork.url.shortener.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlSanitizerTest {

    @Test
    void testEncodeDecodeUrlRoundTrip() {
        String url = "https://example.com/path?name=John Doe&age=30";
        String encodedUrl = UrlSanitizer.encodeUrl(url);
        String decodedUrl = UrlSanitizer.decodeUrl(encodedUrl);

        assertEquals(url, decodedUrl);
    }
}