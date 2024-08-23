package com.upwork.url.shortener.repository;

import com.upwork.url.shortener.domain.Url;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);

    @Modifying
    @Transactional
    @Query("DELETE FROM url u WHERE u.creation_time < :creationTime")
    void deleteByCreationTime(LocalDateTime creationTime);
}
