package com.bqt.flight_booking_server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatusCode();
    }

    public AppException(ErrorCode errorCode, HttpStatus status, Object... args) {
        super(errorCode.formatMessage(args));
        this.status = errorCode.getStatusCode();
    }
}
