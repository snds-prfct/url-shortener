package dev.snds_prfct.url_shortener_query_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlShortenerQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerQueryApplication.class, args);
    }

}
