package com.upwork.url.shortener.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineCacheConfig {

    @Value("${cache.caffeine.maximumSize}")
    private long maximumSize;

    @Value("${cache.caffeine.expireAfterWrite}")
    private long expireAfterWrite;

    public static final String URLS = "urls";

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(URLS);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
