package dev.snds_prfct.url_shortener.query_service.service;

import dev.snds_prfct.url_shortener.query_service.entity.ShortUrl;
import dev.snds_prfct.url_shortener.query_service.exception.UrlNotFoundException;
import dev.snds_prfct.url_shortener.query_service.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static dev.snds_prfct.url_shortener.query_service.redis.CachePrefixes.SHORT_URL_COUNTER_PREFIX;
import static dev.snds_prfct.url_shortener.query_service.redis.CachePrefixes.SHORT_URL_PREFIX;

@Service
@RequiredArgsConstructor
public class UrlShortenerQueryService {

    private final ShortUrlRepository shortUrlRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public String findLongUrl(String shortUrl) {
        String longUrlFromCache = redisTemplate.opsForValue()
                .get(String.format(SHORT_URL_PREFIX + shortUrl));

        if (longUrlFromCache != null) {
            redisTemplate.opsForValue()
                    .increment(SHORT_URL_COUNTER_PREFIX + shortUrl);
            return longUrlFromCache;
        }

        String longUrlFromDb = shortUrlRepository.findLongUrlByShortUrl(shortUrl)
                .map(ShortUrl::getLongUrl)
                .orElseThrow(UrlNotFoundException::new);

        redisTemplate.opsForValue()
                .set(SHORT_URL_PREFIX + shortUrl, longUrlFromDb, Duration.ofDays(30));

        redisTemplate.opsForValue()
                .increment(SHORT_URL_COUNTER_PREFIX + shortUrl);

        return longUrlFromDb;
    }
}
