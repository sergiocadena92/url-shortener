package com.upwork.url.shortener.service;

import com.upwork.url.shortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UrlSchedulerServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlSchedulerService urlSchedulerService;

    @Test
    public void testRemoveOldRecords() {
        urlSchedulerService.removeOldRecords();
        verify(urlRepository, times(1)).deleteByCreationTime(any(LocalDateTime.class));
    }
}