package com.upwork.url.shortener.service;

import com.upwork.url.shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlSchedulerService {

    @Value("${url.persistence.retention.hours}")
    private long retentionHours;

    private final UrlRepository urlRepository;


    @Scheduled(cron = "${url.persistence.purge.cron}")
    public void removeOldRecords() {
        log.info("Start deleting old records");
        urlRepository.deleteByCreationTime(LocalDateTime.now().minusHours(retentionHours));
        log.info("Finish deleting old records");
    }
}
