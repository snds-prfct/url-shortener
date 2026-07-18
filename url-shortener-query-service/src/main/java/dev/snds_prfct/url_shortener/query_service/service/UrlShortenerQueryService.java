package dev.snds_prfct.url_shortener.query_service.service;

import dev.snds_prfct.url_shortener.query_service.entity.ShortUrl;
import dev.snds_prfct.url_shortener.query_service.exception.UrlNotFoundException;
import dev.snds_prfct.url_shortener.query_service.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlShortenerQueryService {

    private final ShortUrlRepository shortUrlRepository;

    public String findLongUrl(String shortUrl) {
        return shortUrlRepository.findLongUrlByShortUrl(shortUrl)
                .map(ShortUrl::getLongUrl)
                .orElseThrow(() -> new UrlNotFoundException());
    }
}
