package dev.snds_prfct.url_shortener.command_service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleUrlNotFoundException(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problemDetail.setTitle("Invalid Arguments");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("details", errors);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(400))
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
