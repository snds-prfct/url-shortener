package dev.snds_prfct.url_shortener.query_service.service;

import dev.snds_prfct.url_shortener.query_service.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlShortenerQueryService {

    private final ShortUrlRepository shortUrlRepository;

    public String findLongUrl(String shortUrl) {
        return null;
    }
}
