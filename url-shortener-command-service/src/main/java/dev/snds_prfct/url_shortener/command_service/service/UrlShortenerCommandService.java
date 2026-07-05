package dev.snds_prfct.url_shortener.command_service.service;

import dev.snds_prfct.url_shortener.command_service.dto.CreateShortUrlRequest;
import dev.snds_prfct.url_shortener.command_service.dto.ShortUrlCreatedResponse;
import dev.snds_prfct.url_shortener.command_service.entity.ShortUrl;
import dev.snds_prfct.url_shortener.command_service.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlShortenerCommandService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlCreatedResponse createShortUrl(CreateShortUrlRequest createShortUrlRequest) {
        ShortUrl createdShortUrl = shortUrlRepository.save(new ShortUrl(UUID.randomUUID().toString().substring(0, 8),
                createShortUrlRequest.longUrl(), Instant.now()));
        return new ShortUrlCreatedResponse(createdShortUrl.getShortUrl());
    }
}
