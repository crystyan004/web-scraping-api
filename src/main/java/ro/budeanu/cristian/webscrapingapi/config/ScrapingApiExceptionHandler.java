package ro.budeanu.cristian.webscrapingapi.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ScrapingApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleConflict(Exception ex, HttpServletRequest request) {
        //todo: create a base exception to be handled here to contain: HTTP error code, internal error code, error message
        //todo: create a error response to be added to the body with fields like: specific error message, specific internal error code, timestamp
        String errorBody = "internal server error";
        log.error(ex.getMessage());
        return ResponseEntity.internalServerError().body(errorBody);
    }
}
