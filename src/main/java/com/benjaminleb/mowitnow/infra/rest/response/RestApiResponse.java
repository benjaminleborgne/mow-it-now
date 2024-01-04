package com.benjaminleb.mowitnow.infra.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class RestApiResponse<T> {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private T response;

    public static RestApiResponse ok(Object response) {
        return RestApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .response(response)
                .status(HttpStatus.OK)
                .build();
    }

    public static RestApiResponse ko(HttpStatus status, String error) {
        return RestApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .response(error)
                .status(status)
                .build();
    }

}
