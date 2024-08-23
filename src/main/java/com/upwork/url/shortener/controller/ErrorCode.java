package com.upwork.url.shortener.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    URL_NOT_FOUND("001", "Url not found"),
    INVALID_URL("002", "Invalid url"),
    GENERAL_ERROR("005", "Unexpected error occurred");

    private final String code;
    private final String message;
}
