package dev.snds_prfct.url_shortener.query_service.controller;

import dev.snds_prfct.url_shortener.query_service.service.UrlShortenerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenerQueryController {

    private final UrlShortenerQueryService urlShortenerQueryService;

    @GetMapping()
    public ResponseEntity<Void> redirectToLongUrl() {
        return null;
    }
}
