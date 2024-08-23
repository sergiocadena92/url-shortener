package com.upwork.url.shortener.service;

import com.upwork.url.shortener.configuration.CaffeineCacheConfig;
import com.upwork.url.shortener.domain.Url;
import com.upwork.url.shortener.exception.UrlNotFoundException;
import com.upwork.url.shortener.repository.UrlRepository;
import com.upwork.url.shortener.util.UrlSanitizer;
import com.upwork.url.shortener.util.UrlShortener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {

    @Value("${url.shortener.base}")
    private String base;

    private final UrlRepository urlRepository;

    @Transactional
    public String shortenUrl(String originalUrl) {
        final String encodedUrl = UrlSanitizer.encodeUrl(originalUrl);
        final String shortUrl = UrlShortener.shortenUrl(encodedUrl);
        final Url url = buildUrl(encodedUrl, shortUrl);
        urlRepository.save(url);
        return String.format(base, shortUrl);
    }

    private static Url buildUrl(String sanitizedUrl, String shortUrl) {
        return Url.builder()
                .originalUrl(sanitizedUrl)
                .shortUrl(shortUrl)
                .creationTime(LocalDateTime.now())
                .build();
    }

    @Cacheable(value = CaffeineCacheConfig.URLS, key = "#shortUrl", unless = "#result == null")
    public String getOriginalUrl(String shortUrl) throws UrlNotFoundException {
        Optional<Url> findShortUrl = urlRepository.findByShortUrl(shortUrl);
        if (findShortUrl.isPresent()) {
            return UrlSanitizer.decodeUrl(findShortUrl.get().getOriginalUrl());
        }
        throw new UrlNotFoundException();
    }

}
