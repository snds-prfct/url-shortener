package dev.snds_prfct.url_shortener.query_service;

import dev.snds_prfct.url_shortener.query_service.exception.UrlNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = UrlNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUrlNotFoundException(UrlNotFoundException urlNotFoundException, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(404));
        problemDetail.setTitle("Url not found");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return ResponseEntity
                .status(HttpStatusCode.valueOf(404))
                .body(problemDetail);
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception exception, WebRequest webRequest) {
        log.error("Internal Server error occurred", exception);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(500));
        problemDetail.setTitle("Something went wrong on our end");
        problemDetail.setInstance(URI.create(webRequest.getContextPath()));

        return ResponseEntity
                .internalServerError()
                .body(problemDetail);
    }
}
