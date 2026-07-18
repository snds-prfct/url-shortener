package dev.snds_prfct.url_shortener.query_service.repository;

import dev.snds_prfct.url_shortener.query_service.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {

    Optional<ShortUrl> findLongUrlByShortUrl(String shortUrl);
}

