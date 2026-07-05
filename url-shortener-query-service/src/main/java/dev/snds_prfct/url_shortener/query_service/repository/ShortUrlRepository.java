package dev.snds_prfct.url_shortener.query_service.repository;

import dev.snds_prfct.url_shortener.query_service.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {

    String findLongUrlByShortUrl(String shortUrl);
}

