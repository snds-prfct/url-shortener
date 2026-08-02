package dev.snds_prfct.url_shortener.query_service.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

import static dev.snds_prfct.url_shortener.query_service.redis.CachePrefixes.SHORT_URL_COUNTER_PREFIX;
import static dev.snds_prfct.url_shortener.query_service.redis.CachePrefixes.SHORT_URL_PREFIX;

@Repository
@RequiredArgsConstructor
public class RedisShortUrlRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Optional<String> getLongUrl(String shortUrl) {
        return Optional.ofNullable(redisTemplate.opsForValue()
                .get(SHORT_URL_PREFIX + shortUrl));
    }

    public void setLongUrlByShortUrl(String shortUrl, String longUrl) {
        redisTemplate.opsForValue()
                .set(SHORT_URL_PREFIX + shortUrl, longUrl, Duration.ofDays(30));
    }

    public void incrementShortUrlCounter(String shortUrl) {
        redisTemplate.opsForValue()
                .increment(SHORT_URL_COUNTER_PREFIX + shortUrl);
    }

}
