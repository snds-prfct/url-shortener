package dev.snds_prfct.url_shortener.command_service.controller;

import dev.snds_prfct.url_shortener.command_service.dto.CreateShortUrlRequest;
import dev.snds_prfct.url_shortener.command_service.dto.ShortUrlCreatedResponse;
import dev.snds_prfct.url_shortener.command_service.service.UrlShortenerCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenerCommandController {

    private final UrlShortenerCommandService urlShortenerCommandService;

    @PostMapping("/short")
    public ResponseEntity<ShortUrlCreatedResponse> createShortUrl(@RequestBody CreateShortUrlRequest createShortUrlRequest) {
        return ResponseEntity.ok(urlShortenerCommandService.createShortUrl(createShortUrlRequest));
    }
}
