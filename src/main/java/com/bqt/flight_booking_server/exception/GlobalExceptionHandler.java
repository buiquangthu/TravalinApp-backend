package com.bqt.flight_booking_server.exception;

import com.bqt.flight_booking_server.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    // xử lý validation error từ @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            details.put(error.getField(), error.getDefaultMessage());
        });

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),  // 400
                "VALIDATION_ERROR",
                "Lỗi xác thực dữ liệu đầu vào",
                details
        );

        return ResponseEntity.badRequest().body(response);
    }


    // Xử lý lỗi nghiệp vụ
    @ExceptionHandler(AppException.class)
    ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse response = new ErrorResponse(
                errorCode.getStatusCode().value(), // Lấy status từ ErrorCode
                errorCode.name(),
                ex.getMessage(),
                ex.getDetails()
        );

        return ResponseEntity.status(errorCode.getStatusCode()).body(response);
    }


    // Xử lý lỗi hệ thống
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Unhandled exception: ", ex);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // 500
                "INTERNAL_ERROR",
                "Lỗi hệ thống",
                null
        );

        return ResponseEntity.internalServerError().body(response);
    }
}
