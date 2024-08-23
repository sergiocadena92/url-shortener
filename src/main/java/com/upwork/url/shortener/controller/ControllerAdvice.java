package com.upwork.url.shortener.controller;

import com.upwork.url.shortener.exception.UrlNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UrlNotFoundException.class)
    public ErrorResponse handleUrlNotFoundException() {
        return ErrorResponse.builder()
                .code(ErrorCode.URL_NOT_FOUND.getCode())
                .message(ErrorCode.URL_NOT_FOUND.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(ErrorCode.INVALID_URL.getCode())
                .message(ErrorCode.INVALID_URL.getMessage())
                .details(
                        result.getFieldErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList())
                )
                .dateTime(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneralException(Exception exception) {
        return ErrorResponse.builder()
                .code(ErrorCode.GENERAL_ERROR.getCode())
                .message(ErrorCode.GENERAL_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .dateTime(LocalDateTime.now())
                .build();
    }

}
