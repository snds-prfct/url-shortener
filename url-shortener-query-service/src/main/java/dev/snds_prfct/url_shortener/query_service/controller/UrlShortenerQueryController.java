package dev.snds_prfct.url_shortener.query_service.controller;

import dev.snds_prfct.url_shortener.query_service.service.UrlShortenerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UrlShortenerQueryController {

    private final UrlShortenerQueryService urlShortenerQueryService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortUrl) {
        String longUrl = urlShortenerQueryService.findLongUrl(shortUrl);
        return ResponseEntity
                .status(HttpStatusCode.valueOf(302))
                .location(URI.create(longUrl))
                .build();
    }
}
