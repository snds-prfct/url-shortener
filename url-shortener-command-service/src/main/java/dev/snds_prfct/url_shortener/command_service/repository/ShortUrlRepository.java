package dev.snds_prfct.url_shortener.command_service.repository;

import dev.snds_prfct.url_shortener.command_service.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {
}
