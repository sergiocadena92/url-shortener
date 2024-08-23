package com.upwork.url.shortener.common;

import com.upwork.url.shortener.util.UrlShortener;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlProperty {
    @Value("${url.shortener.alphabet}")
    private String alphabet;

    @Value("${url.shortener.length}")
    private int length;

    @PostConstruct
    private void init(){
        UrlShortener.setAlphabet(alphabet);
        UrlShortener.setLength(length);
    }
}
