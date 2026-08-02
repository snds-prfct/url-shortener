package dev.snds_prfct.url_shortener.query_service.service;

import dev.snds_prfct.url_shortener.query_service.entity.ShortUrl;
import dev.snds_prfct.url_shortener.query_service.exception.UrlNotFoundException;
import dev.snds_prfct.url_shortener.query_service.redis.RedisShortUrlRepository;
import dev.snds_prfct.url_shortener.query_service.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerQueryService {

    private final ShortUrlRepository shortUrlRepository;
    private final RedisShortUrlRepository redisShortUrlRepository;

    public String findLongUrl(String shortUrl) {
        Optional<String> longUrlFromCache = redisShortUrlRepository.getLongUrl(shortUrl);
        if (longUrlFromCache.isPresent()) {
            redisShortUrlRepository.incrementShortUrlCounter(shortUrl);
            return longUrlFromCache.get();
        }

        String longUrlFromDb = findLongUrlInDb(shortUrl);

        redisShortUrlRepository.setLongUrlByShortUrl(shortUrl, longUrlFromDb);
        redisShortUrlRepository.incrementShortUrlCounter(shortUrl);

        return longUrlFromDb;
    }

    private String findLongUrlInDb(String shortUrl) {
        return shortUrlRepository.findLongUrlByShortUrl(shortUrl)
                .map(ShortUrl::getLongUrl)
                .orElseThrow(UrlNotFoundException::new);
    }
}
