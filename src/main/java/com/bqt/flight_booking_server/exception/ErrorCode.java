package com.bqt.flight_booking_server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    // Lỗi he thong chung
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Uncategorized error"),

    // Lỗi đầu vào
    INVALID_KEY(HttpStatus.BAD_REQUEST, "Invalid key"),
    USERNAME_INVALID(HttpStatus.BAD_REQUEST, "Username must be at least {min} characters"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Password must be at least {min} characters"),
    INVALID_DOB(HttpStatus.BAD_REQUEST, "Your age must be at least {min}"),

    // Lỗi người dùng
    USER_EXISTED(HttpStatus.CONFLICT, "User already exists"),
    USER_NOT_EXISTED(HttpStatus.NOT_FOUND, "User not found"),

    // Lỗi xác thực & quyền
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "Unauthenticated"),
    UNAUTHORIZED(HttpStatus.FORBIDDEN, "You do not have permission");

    private final HttpStatus statusCode;
    private final String message;

    ErrorCode(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Cho phép thay thế giá trị placeholder {min} khi cần
    public String formatMessage(Object... args) {
        return String.format(message.replace("{min}", "%s"), args);
    }
}
