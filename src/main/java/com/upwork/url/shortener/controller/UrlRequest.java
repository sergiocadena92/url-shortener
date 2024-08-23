package com.upwork.url.shortener.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest {

    @NotBlank(message = "Field originalUrl cannot be empty or null")
    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "Invalid URL format"
    )
    private String originalUrl;
}
