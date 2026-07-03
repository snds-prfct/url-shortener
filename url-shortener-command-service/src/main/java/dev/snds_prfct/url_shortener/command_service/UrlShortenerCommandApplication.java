package dev.snds_prfct.url_shortener.command_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlShortenerCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerCommandApplication.class, args);
    }

}
