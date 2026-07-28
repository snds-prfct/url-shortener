package dev.snds_prfct.url_shortener.command_service.controller;

import dev.snds_prfct.url_shortener.command_service.dto.CreateShortUrlRequest;
import dev.snds_prfct.url_shortener.command_service.dto.ShortUrlCreatedResponse;
import dev.snds_prfct.url_shortener.command_service.service.UrlShortenerCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import tools.jackson.databind.ObjectMapper;

import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class UrlShortenerCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UrlShortenerCommandService urlShortenerCommandService;

    @Test
    void createShortUrlWhenInvalidUrlIsProvided() throws Exception {
        // given
        CreateShortUrlRequest createShortUrlRequest = new CreateShortUrlRequest("invalid-url");
        String requestBodyJson = objectMapper.writeValueAsString(createShortUrlRequest);

        // when
        ResultActions result = mockMvc.perform(post("/short")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson));

        // then
        result
                .andExpect(status().isBadRequest());
    }

    @Test
    void createShortUrlWhenBlankUrlIsProvided() throws Exception {
        // given
        CreateShortUrlRequest createShortUrlRequest = new CreateShortUrlRequest(" ");
        String requestBodyJson = objectMapper.writeValueAsString(createShortUrlRequest);

        // when
        ResultActions result = mockMvc.perform(post("/short")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson));

        // then
        result
                .andExpect(status().isBadRequest());
    }

    @Test
    void createShortUrlWhenTooLongUrlIsProvided() throws Exception {
        // given
        String longUrl = "http://www.company.com/products/" + generateAlphanumericRandomString(1100);
        CreateShortUrlRequest createShortUrlRequest = new CreateShortUrlRequest(longUrl);
        String requestBodyJson = objectMapper.writeValueAsString(createShortUrlRequest);

        // when
        ResultActions result = mockMvc.perform(post("/short")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson));

        // then
        result
                .andExpect(status().isBadRequest());
    }

    @Test
    void createShortUrlWhenValidUrlIsProvided() throws Exception {
        // given
        CreateShortUrlRequest createShortUrlRequest = new CreateShortUrlRequest("http://www.company.com/products/1");
        String requestBodyJson = objectMapper.writeValueAsString(createShortUrlRequest);

        String shortUrl = "aabbccdd";
        when(urlShortenerCommandService.createShortUrl(createShortUrlRequest))
                .thenReturn(new ShortUrlCreatedResponse(shortUrl));

        // when
        ResultActions result = mockMvc.perform(post("/short")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson));

        // then
        result
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.shortUrl").value(shortUrl));
    }

    private String generateAlphanumericRandomString(int size) {
        return new Random().ints('0', 'z' + 1)
                .filter(Character::isLetterOrDigit)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}