package com.upwork.url.shortener.controller;

import com.upwork.url.shortener.exception.UrlNotFoundException;
import com.upwork.url.shortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/url")
    public ResponseEntity<String> shortenUrl(@Valid @RequestBody UrlRequest urlRequest){
        final String originalUrl =  urlService.shortenUrl(urlRequest.getOriginalUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(originalUrl);
    }

    @RequestMapping("/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws UrlNotFoundException, IOException {
        final String originalUrl = urlService.getOriginalUrl(shortUrl);
        response.sendRedirect(originalUrl);
    }
}
