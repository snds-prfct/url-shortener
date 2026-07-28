package dev.snds_prfct.url_shortener.command_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateShortUrlRequest(
        @NotBlank
        @Size(max = 1000)
        @URL
        String longUrl) {
}
