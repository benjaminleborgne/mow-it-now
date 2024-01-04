package com.benjaminleb.mowitnow.infra.rest.handler;

import com.benjaminleb.mowitnow.infra.rest.response.RestApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleBadInstructionsFormat(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestApiResponse.ko(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestApiResponse.ko(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
