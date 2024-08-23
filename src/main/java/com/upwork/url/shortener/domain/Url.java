package com.upwork.url.shortener.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("url")
@Getter
@Builder
public class Url {
    @Id
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime creationTime;
}
